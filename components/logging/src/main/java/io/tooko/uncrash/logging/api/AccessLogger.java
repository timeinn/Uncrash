package io.tooko.uncrash.logging.api;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface AccessLogger {

    String value();

    String[] describe() default "";

    boolean ignore() default false;

}
