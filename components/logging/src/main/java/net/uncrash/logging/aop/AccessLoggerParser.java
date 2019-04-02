package net.uncrash.logging.aop;

import net.uncrash.core.boost.aop.MethodInterceptorHolder;
import net.uncrash.logging.api.LoggerDefine;

import java.lang.reflect.Method;

public interface AccessLoggerParser {

    boolean support(Class clazz, Method method);

    LoggerDefine parse(MethodInterceptorHolder holder);
}
