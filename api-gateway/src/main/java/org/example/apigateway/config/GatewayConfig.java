package org.example.apigateway.config;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.example.apigateway.JWT.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.WebFilter;

@Configuration
public class GatewayConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        System.out.println("Custom RouteLocator\n\n");
        return builder.routes()
                .route("user-service", r -> r.path("/api-gateway/user-service/**")
                        .filters(f -> f
                                .filter(jwtAuthenticationFilter)
                                .stripPrefix(1)
                        )
                        .uri("http://user-service:8085"))

                .route("trek-service", r -> r.path("/api-gateway/trek-service/**")
                        .filters(f -> f
                                .filter(jwtAuthenticationFilter)
                                .stripPrefix(1)
                        )
                        .uri("http://trek-service:8081"))
                .route("media-service", r -> r.path("/api-gateway/media-service/**")
                        .filters(f -> f
                                .filter(jwtAuthenticationFilter)
                                .stripPrefix(1)
                        )
                        .uri("http://media-service:9650"))

                .route("auth-service", r -> r.path("/api-gateway/auth-service/**")
                        .filters(f -> f
                                .stripPrefix(1)
                        )
                        .uri("http://auth-service:8080"))


                .route("chat-service", r -> r.path("/api-gateway/chat-service/**")
                        .filters(f -> f
                                .stripPrefix(1)
                        )
                        .uri("http://chat-service:8087"))

                .route("eclient", r -> r.path("/eclient/**")
                        .filters(f -> f.filter(jwtAuthenticationFilter))
                        .uri("lb://eclient-service"))

                .build();
    }

    @Bean
    public WebFilter metricsWebFilter(MeterRegistry registry) {
        return (exchange, chain) -> {
            String path = exchange.getRequest().getPath().value();
            Timer.Sample sample = Timer.start(registry);
            return chain.filter(exchange).doOnSuccess(aVoid -> {
                String method = exchange.getRequest().getMethod() != null
                        ? exchange.getRequest().getMethod().name()
                        : "UNKNOWN";
                String status = exchange.getResponse().getStatusCode() != null
                        ? String.valueOf(exchange.getResponse().getStatusCode().value())
                        : "UNKNOWN";

                sample.stop(Timer.builder("http_server_requests")
                        .tag("method", method)
                        .tag("uri", path)
                        .tag("status", status)
                        .register(registry));
            });
        };
    }

}
