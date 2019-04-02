package io.tooko.uncrash.logging.aop;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ImportAutoConfiguration(AopAccessLoggerSupportAutoConfiguration.class)
public @interface EnableAccessLogger {
}
