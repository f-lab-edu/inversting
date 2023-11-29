package com.investing.servicegateway.route;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoute {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", routes -> routes.path("/users/**")
                        .filters(f -> f.circuitBreaker(c -> c.setName("user-service-circuitBreaker")
                                .setFallbackUri("forward:/fallback")))
                        .uri("http://localhost:8080"))
                .route("stock-service", routes -> routes.path("/stocks/**")
                        .filters(f -> f.circuitBreaker(c -> c.setName("stock-service-circuitBreaker")
                                .setFallbackUri("forward:/fallback")))
                        .uri("http://localhost:8089"))
                .build();
    }

}
