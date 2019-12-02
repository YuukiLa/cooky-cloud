package com.yuuki.cooky.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.security.Principal;

/**
 * 给请求头添加用户label
 */
@Component
public class UserLabelGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        final Principal[] principal = new Principal[1];
        exchange.getPrincipal().subscribe((v) -> {
           principal[0] = v;
        });
        if (principal[0] != null) {

            HttpHeaders headers = exchange.getRequest().getHeaders();
            headers.set("X-USER-NAME", principal[0].getName());

        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
