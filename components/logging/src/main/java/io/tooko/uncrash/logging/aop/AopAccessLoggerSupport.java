package io.tooko.uncrash.logging.aop;

import io.tooko.core.boost.aop.MethodInterceptorHolder;
import io.tooko.core.utils.WebUtil;
import io.tooko.core.utils.id.IDGenerator;
import io.tooko.uncrash.logging.api.AccessLoggerInfo;
import io.tooko.uncrash.logging.api.AccessLoggerListener;
import io.tooko.uncrash.logging.api.LoggerDefine;
import io.tooko.uncrash.logging.api.events.AccessLoggerAfterEvent;
import io.tooko.uncrash.logging.api.events.AccessLoggerBeforeEvent;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.Ordered;
import org.springframework.util.ClassUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AopAccessLoggerSupport extends StaticMethodMatcherPointcutAdvisor {

    @Autowired(required = false)
    private final List<AccessLoggerListener> listeners = new ArrayList<>();

    @Autowired(required = false)
    private final List<AccessLoggerParser> loggerParsers = new ArrayList<>();

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public AopAccessLoggerSupport addListener(AccessLoggerListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
        return this;
    }

    public AopAccessLoggerSupport addParser(AccessLoggerParser parser) {
        if (!loggerParsers.contains(parser)) {
            loggerParsers.add(parser);
        }
        return this;
    }

    public AopAccessLoggerSupport() {
        setAdvice((MethodInterceptor) methodInvocation -> {
            MethodInterceptorHolder holder = MethodInterceptorHolder.create(methodInvocation);
            AccessLoggerInfo info = createLogger(holder);
            Object response;
            try {
                // 触发事件 日志监听处理前
                eventPublisher.publishEvent(new AccessLoggerBeforeEvent(info));
                listeners.forEach(listener -> listener.onLogBefore(info));
                response = methodInvocation.proceed();
                info.setResponse(response);
            } catch (Throwable e) {
                info.setException(e);
                throw e;
            } finally {
                info.setResponseTime(System.currentTimeMillis());

                // 触发事件 日志监听处理后
                eventPublisher.publishEvent(new AccessLoggerAfterEvent(info));
                listeners.forEach(listener -> listener.onLogger(info));
            }
            return response;
        });
    }

    protected AccessLoggerInfo createLogger(MethodInterceptorHolder holder) {
        AccessLoggerInfo info = new AccessLoggerInfo();
        info.setId(IDGenerator.MD5.generate());
        info.setRequestTime(System.currentTimeMillis());
        LoggerDefine define = loggerParsers.stream()
            .filter(parser -> parser.support(ClassUtils.getUserClass(holder.getTarget()), holder.getMethod()))
            .findAny()
            .map(parser -> parser.parse(holder))
            .orElse(null);

        if (define != null) {
            info.setAction(define.getAction());
            info.setDescribe(define.getDescribe());
        }
        info.setParameters(holder.getArgs());
        info.setTarget(holder.getTarget().getClass());
        info.setMethod(holder.getMethod());

        HttpServletRequest request = WebUtil.getHttpServletRequest();
        if (null != request) {
            info.setHttpHeaders(WebUtil.getHeaders(request));
            info.setIp(WebUtil.getIpAddr(request));
            info.setHttpMethod(request.getMethod());
            info.setUrl(request.getRequestURL().toString());
        }
        return info;

    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }


    @Override
    public boolean matches(Method method, Class<?> aClass) {
        return loggerParsers.stream().anyMatch(parser -> parser.support(aClass, method));
    }
}
