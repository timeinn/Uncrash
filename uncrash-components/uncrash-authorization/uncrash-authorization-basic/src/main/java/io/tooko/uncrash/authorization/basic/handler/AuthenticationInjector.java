package io.tooko.uncrash.authorization.basic.handler;

import io.tooko.uncrash.authorization.AuthenticationHolder;
import io.tooko.uncrash.authorization.AuthenticationUser;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

/**
 * @author tooko
 */
@Slf4j
@Aspect
@Configuration
public class AuthenticationInjector {

    @Pointcut("execution(* io.tooko..api.controller..*(io.tooko.uncrash.authorization.AuthenticationUser,..))")
    public void injectPoint() {
    }

    @Before(value = "injectPoint() && args(authenticationUser,..)")
    public void beforeController(JoinPoint joinPoint, AuthenticationUser authenticationUser) {
        log.debug("Pointcut before: {}", joinPoint);

        authenticationUser = (AuthenticationUser) AuthenticationHolder.get();

        log.debug("Pointcut proceed AuthenticationUser: {}", authenticationUser);
    }

}
