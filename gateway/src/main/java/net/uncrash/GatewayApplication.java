package net.uncrash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.StripPrefixGatewayFilterFactory;
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

    /**
     * Router locator configuration
     *
     * @param builder Route Locator Builder
     * @return Route Locator
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        //@formatter:off
        return builder.routes()
            .route("user-api", r -> r.path("/v1/user/**")
                .uri("https://api.uncrash.net"))
            .route("agent-api", r -> r.path("/v1/agent/**")
                .uri("lb://uncrash-agent-service/")
                .filters(new StripPrefixGatewayFilterFactory()
                    .apply(config -> config.setParts(1)))
            ) // https://api.uncrash.net

            .build();
        //@formatter:on
    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
