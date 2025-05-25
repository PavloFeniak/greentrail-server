package org.example.apigateway.config;

import org.example.apigateway.JWT.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r.path("/user-service/**")
                        .filters(f -> f
                                .filter(jwtAuthenticationFilter)
                                .stripPrefix(1)
                        )
                        .uri("lb://user-service"))

                .route("trek-service", r -> r.path("/trek-service/**")
                        .filters(f -> f
                                .filter(jwtAuthenticationFilter)
                                .stripPrefix(1)
                        )
                        .uri("lb://trek-service"))
                .route("media-service", r -> r.path("/media-service/**")
                        .filters(f -> f
                                .filter(jwtAuthenticationFilter)
                                .stripPrefix(1)
                        )
                        .uri("lb://media-service"))

                .route("auth-service", r -> r.path("/auth/**")
                        .uri("lb://auth-service"))

                .route("eclient", r -> r.path("/eclient/**")
                        .filters(f -> f.filter(jwtAuthenticationFilter))
                        .uri("lb://eclient-service"))

                .build();
    }
}
