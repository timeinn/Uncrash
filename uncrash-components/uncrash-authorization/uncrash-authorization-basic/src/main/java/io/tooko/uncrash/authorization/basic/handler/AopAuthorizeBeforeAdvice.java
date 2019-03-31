package io.tooko.uncrash.authorization.basic.handler;

import io.tooko.core.utils.JSONUtil;
import io.tooko.uncrash.authorization.annotation.Authorize;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author tooko
 */
@Slf4j
@Component
public class AopAuthorizeBeforeAdvice implements MethodBeforeAdvice, MethodInterceptor {

    @Override
    public void before(Method method, Object[] objects, Object target) throws Throwable {

        log.info("invoke: {} {}", method.getName(), JSONUtil.toJSON(objects));
        Annotation[][] annotations = method.getParameterAnnotations();

        log.info("Annotation[][]: {}", annotations);
        log.info("target: {}", target.getClass());
        /*
        for (int i = 0; i < annotations.length; i++) {
            for (int j = 0; j < annotations[i].length; j++) {
                Annotation annotation = annotations[i][j];
                if (annotation instanceof Authorize) {
                    Authorize authorize = ((Authorize) annotation);
                    String[] permission = authorize.permission();
                    String[] actions = authorize.action();
                    log.warn("permission: {}, actions: {}", permission, actions);
                }
            }
        }*/
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        Method method = methodInvocation.getMethod();

        log.warn("method: {}", method);

        Annotation[][] annotations = method.getParameterAnnotations();
        for (int i = 0; i < annotations.length; i++) {
            for (int j = 0; j < annotations[i].length; j++) {
                Annotation annotation = annotations[i][j];
                if (annotation instanceof Authorize) {
                    Authorize authorize = ((Authorize) annotation);
                    String[] permission = authorize.permission();
                    String[] actions = authorize.action();
                    log.warn("permission: {}, actions: {}", permission, actions);
                }
            }
        }

        return methodInvocation.proceed();
    }

}
