package io.tooko.uncrash.logging.aop;

import io.tooko.core.boost.aop.MethodInterceptorHolder;
import io.tooko.uncrash.logging.api.AccessLogger;
import io.tooko.uncrash.logging.api.LoggerDefine;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.stream.Stream;

public class DefaultAccessLoggerParser implements AccessLoggerParser {

    @Override
    public boolean support(Class clazz, Method method) {
        AccessLogger ann = AnnotationUtils.findAnnotation(method, AccessLogger.class);
        return null != ann && !ann.ignore();
    }

    @Override
    public LoggerDefine parse(MethodInterceptorHolder holder) {
        AccessLogger methodAnn = holder.findMethodAnnotation(AccessLogger.class);
        AccessLogger classAnn = holder.findClassAnnotation(AccessLogger.class);
        String action = Stream.of(classAnn, methodAnn)
            .filter(Objects::nonNull)
            .map(AccessLogger::value)
            .reduce((c, m) -> c.concat("-").concat(m))
            .orElse("");
        String describe = Stream.of(classAnn, methodAnn)
            .filter(Objects::nonNull)
            .map(AccessLogger::describe)
            .flatMap(Stream::of)
            .reduce((c, s) -> c.concat("\n").concat(s))
            .orElse("");
        return new LoggerDefine(action, describe);
    }
}
