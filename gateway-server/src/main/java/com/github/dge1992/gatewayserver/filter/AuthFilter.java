package com.github.dge1992.gatewayserver.filter;

import com.alibaba.fastjson.JSON;
import com.github.dge1992.common.domain.User;
import com.github.dge1992.commonserver.jwt.JwtProperties;
import com.github.dge1992.commonserver.jwt.JwtTokenHandler;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 
 * @author 董感恩
 * @date 2020-02-03 21:45:54
 * @desc 认证拦截器
 **/
//@Component
public class AuthFilter implements GlobalFilter, Ordered {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private JwtTokenHandler jwtTokenHandler;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Route gatewayUrl = exchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        ServerHttpRequest request = exchange.getRequest();
        //判断请求是否是认证请求
        String path = request.getURI().getPath();
        String authPath = "/system/" + jwtProperties.getAuthPath();
        if(path.equals(authPath)){
            return chain.filter(exchange);
        }
        //开始认证
        HttpHeaders header = request.getHeaders();
        final String requestHeader = header.getFirst(jwtProperties.getHeader());
        String authToken = null;
        User user = null;
        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            authToken = requestHeader.substring(7);
            //验证token是否过期,包含了验证jwt是否正确
            try {
                user = jwtTokenHandler.getClaimAndTokenNotExpired(authToken);
                if (user == null) {
                    throw new RuntimeException("user not exist, please check");
                }
            } catch (JwtException e) {
                //有异常就是token解析失败
                throw new RuntimeException("user not exist, please check");
            }
        } else {
            //header没有带Bearer字段
            throw new RuntimeException("user not exist, please check");
        }
        //拼装数据
        ServerHttpRequest.Builder mutate = request.mutate();
        mutate.header("x-user", JSON.toJSONString(user));
        mutate.header("x-user-serviceName", gatewayUrl.getUri().getHost());
        ServerHttpRequest buildRequest =  mutate.build();
        return chain.filter(exchange.mutate().request(buildRequest).build());
    }

    @Override
    public int getOrder() {
        return -400;
    }
}
