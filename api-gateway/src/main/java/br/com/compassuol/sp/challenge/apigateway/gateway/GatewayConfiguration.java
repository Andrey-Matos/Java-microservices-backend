package br.com.compassuol.sp.challenge.apigateway.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {


    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/products/**")
                        .uri("lb://ms-products"))
                .route(p -> p.path("/orders/**")
                        .uri("lb://ms-orders"))
                .build();
    }

}