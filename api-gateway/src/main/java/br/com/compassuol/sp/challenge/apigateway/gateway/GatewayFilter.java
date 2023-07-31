package br.com.compassuol.sp.challenge.apigateway.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.logging.LoggingRebinder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class GatewayFilter implements GlobalFilter {

    private Logger logger = LoggerFactory.getLogger(LoggingRebinder.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
                             GatewayFilterChain chain) {
        logger.info("Path of the request received -> {}",
                exchange.getRequest().getPath());
        return chain.filter(exchange);
    }

}