package com.elliot.gatewaycenter.filter;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.Map;


/**
 * 日志打印过滤器
 */
@Slf4j
@Component
public class LogGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取请求信息
        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();
        HttpMethod method = request.getMethod();
        HttpHeaders headers = request.getHeaders();

        log.info("LogGlobalFilter - uri: {}", uri);
        log.info("LogGlobalFilter - method: {}", method.name());

        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            log.info("LogGlobalFilter - headers: {} {}", entry.getKey(), entry.getValue());
        }

        // 放行请求
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
