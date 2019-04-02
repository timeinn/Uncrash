package io.tooko.uncrash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * Gateway for Uncrash
 *
 * @author Sendya
 * @author Acris
 * @date 2019/04/02
 */
@SpringBootApplication
public class GatewayApplication {


    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        //@formatter:off
        return builder.routes()
            .route("user-api", r -> r.path("/user/**")
                .uri("https://user.api.uncrash.net"))
            .route("agent-api", r -> r.path("/agent/**")
                .uri("https://agent.api.uncrash.net"))
            .build();
        //@formatter:on
    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
