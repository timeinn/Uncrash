package net.uncrash.authorization.basic.handler;

import lombok.extern.slf4j.Slf4j;
import net.uncrash.authorization.AuthenticationHolder;
import net.uncrash.authorization.AuthenticationUser;
import net.uncrash.authorization.basic.entity.DefaultUser;
import net.uncrash.core.utils.id.IDGenerator;
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
        // 设置一个默认值
        if (authenticationUser == null) {
            authenticationUser = new AuthenticationUser();
            authenticationUser.setUser(DefaultUser.builder()
                .id(IDGenerator.UUID_NO_SEPARATOR.generate())
                .build());
        }

        log.debug("Pointcut proceed AuthenticationUser: {}", authenticationUser);
    }

}
