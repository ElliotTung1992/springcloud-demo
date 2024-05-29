package com.elliot.gatewaycenter.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Objects;

@Slf4j
@Configuration
public class RateConfiguration {

    @Bean(name = "ipKeyResolver")
    public KeyResolver ipKeyResolver() {
        return exchange -> {
            ServerHttpRequest request = exchange.getRequest();
            URI uri = request.getURI();
            String hostName = Objects.requireNonNull(request.getRemoteAddress()).getHostName();
            log.info("hostName:{} uri:{}", hostName, uri.getPath());
            return Mono.just(hostName);
        };
    }
}
