package io.tooko.uncrash.logging.aop;

import io.tooko.core.boost.aop.MethodInterceptorHolder;
import io.tooko.uncrash.logging.api.LoggerDefine;

import java.lang.reflect.Method;

public interface AccessLoggerParser {

    boolean support(Class clazz, Method method);

    LoggerDefine parse(MethodInterceptorHolder holder);
}
