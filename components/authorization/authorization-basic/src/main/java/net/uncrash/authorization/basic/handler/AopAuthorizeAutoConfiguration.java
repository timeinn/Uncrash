package net.uncrash.authorization.basic.handler;

import net.uncrash.authorization.basic.aop.AopMethodAuthorizeDefinitionParser;
import net.uncrash.authorization.basic.aop.DefaultAopMethodAuthorizeDefinitionParser;
import net.uncrash.authorization.handler.AuthorizingHandler;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Sendya
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
    @ConfigurationProperties(prefix = "net.uncrash.authorize")
    public AopAuthorizingController aopAuthorizingController(AuthorizingHandler authorizingHandler,
                                                             AopMethodAuthorizeDefinitionParser aopMethodAuthorizeDefinitionParser) {
        return new AopAuthorizingController(authorizingHandler,
            aopMethodAuthorizeDefinitionParser);
    }
}
