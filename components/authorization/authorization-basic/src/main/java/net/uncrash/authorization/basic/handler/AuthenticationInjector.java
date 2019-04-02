package net.uncrash.authorization.basic.handler;

import net.uncrash.authorization.AuthenticationHolder;
import net.uncrash.authorization.AuthenticationUser;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

/**
 * @author Sendya
 */
@Slf4j
@Aspect
@Configuration
public class AuthenticationInjector {

    @Pointcut("execution(* net.uncrash..api.controller..*(net.uncrash.authorization.AuthenticationUser,..))")
    public void injectPoint() {
    }

    @Before(value = "injectPoint() && args(authenticationUser,..)")
    public void beforeController(JoinPoint joinPoint, AuthenticationUser authenticationUser) {
        log.debug("Pointcut before: {}", joinPoint);

        authenticationUser = (AuthenticationUser) AuthenticationHolder.get();

        log.debug("Pointcut proceed AuthenticationUser: {}", authenticationUser);
    }

}
