package io.tooko.uncrash.authorization.basic.handler;

import io.tooko.uncrash.authorization.basic.aop.AopMethodAuthorizeDefinitionParser;
import io.tooko.uncrash.authorization.basic.aop.DefaultAopMethodAuthorizeDefinitionParser;
import io.tooko.uncrash.authorization.handler.AuthorizingHandler;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tooko
 */
@Configuration
@AutoConfigureAfter(AuthorizingHandlerAutoConfiguration.class)
public class AopAuthorizeAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(AopMethodAuthorizeDefinitionParser.class)
    public DefaultAopMethodAuthorizeDefinitionParser defaultAopMethodAuthorizeDefinitionParser() {
        return new DefaultAopMethodAuthorizeDefinitionParser();
    }

    @Bean
    @ConfigurationProperties(prefix = "tooko.authorize")
    public AopAuthorizingController aopAuthorizingController(AuthorizingHandler authorizingHandler,
                                                             AopMethodAuthorizeDefinitionParser aopMethodAuthorizeDefinitionParser) {
        return new AopAuthorizingController(authorizingHandler,
            aopMethodAuthorizeDefinitionParser);
    }
}
