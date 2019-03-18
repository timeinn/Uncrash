package io.tooko.uncrash.authorization.basic.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tooko
 */
@Slf4j
@Configuration
public class AuthorizingHandlerAutoConfiguration {

    @Bean
    public DefaultAuthorizingHandler authorizingHandler() {
        log.info("::::::DefaultAuthorizingHandler::::::");
        return new DefaultAuthorizingHandler();
    }

}
