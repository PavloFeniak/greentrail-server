package org.example.apigateway.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.http.HttpHeaders;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class JwtAuthenticationFilter implements GatewayFilter {

    private final String SECRET_KEY = "jsvdjkb7ghbsjdbdhvbihiowh9ewy7rgiuogcosgdfgwepfg";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("INJWTFILTER");

        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        if (    path.startsWith("/api-gateway/auth-service/**")||
                path.startsWith("/api-gateway/trek-service/treks/get-all-treks")||
                path.startsWith("/api-gateway/user-service/users/user-by-email")||
                path.startsWith("/api-gateway/trek-service/treks/get-part-of-all-treks")||
                path.matches("/api-gateway/trek-service/treks/\\d+")) {
            return chain.filter(exchange);
        }

        // GEt Jwt from headers
        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            System.out.println("Unauthorized");
            return exchange.getResponse().setComplete();
        }

        String token = authHeader.substring(7);

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            System.out.println("JWT Claims:");
            claims.forEach((key, value) -> System.out.println(key + ": " + value));

            String email = claims.getSubject();

            ServerHttpRequest modifiedRequest = request.mutate()
                    .header("X-User-Email", email)
                    .build();

            return chain.filter(exchange.mutate().request(modifiedRequest).build());

        } catch (Exception e) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }
}
