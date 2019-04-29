package net.uncrash.authorization.basic.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Sendya
 */
@Configuration
public class JwtAutoConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "uncrash.authorize.jwt")
    public JwtConfig jwtConfig() {
        return new JwtConfig();
    }
}
