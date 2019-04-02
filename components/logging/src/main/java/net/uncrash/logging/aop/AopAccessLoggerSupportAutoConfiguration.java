package net.uncrash.logging.aop;

import net.uncrash.logging.api.AccessLoggerListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnClass(AccessLoggerListener.class)
@Configuration
public class AopAccessLoggerSupportAutoConfiguration {

    @Bean
    public AopAccessLoggerSupport aopAccessLoggerSupport() {
        return new AopAccessLoggerSupport();
    }

    @Bean
    public DefaultAccessLoggerParser defaultAccessLoggerParser() {
        return new DefaultAccessLoggerParser();
    }

    @Bean
    @ConditionalOnClass(name = "io.swagger.annotations.Api")
    public SwaggerAccessLoggerParser swaggerAccessLoggerParser() {
        return new SwaggerAccessLoggerParser();
    }
}
