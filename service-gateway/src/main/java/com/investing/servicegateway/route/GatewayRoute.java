package com.investing.servicegateway.route;

import com.investing.servicegateway.global.config.ServiceUrlConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GatewayRoute {

    private final ServiceUrlConfig serviceUrlConfig;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", routes -> routes.path("/users/**")
                        .filters(f -> f.circuitBreaker(c -> c.setName("user-service-circuitBreaker")
                                .setFallbackUri("forward:/fallback")))
                        .uri(serviceUrlConfig.getUserServiceUrl()))
                .route("stock-service", routes -> routes.path("/stocks/**")
                        .filters(f -> f.circuitBreaker(c -> c.setName("stock-service-circuitBreaker")
                                .setFallbackUri("forward:/fallback")))
                        .uri(serviceUrlConfig.getStockServiceUrl()))
                .build();
    }

}
