package com.github.dge1992.gatewayserver.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * @author 董感恩
 * @date 2020-01-16 11:12
 * @desc 限流配置
 */
@Configuration
public class LimiterConfig {

    //根据请求参数中的 name 字段来限流
    @Bean
    public KeyResolver testKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("name"));
    }

    @Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }
}
