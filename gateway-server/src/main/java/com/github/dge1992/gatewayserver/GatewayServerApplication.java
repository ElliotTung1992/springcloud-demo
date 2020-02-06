package com.github.dge1992.gatewayserver;

import com.github.dge1992.gatewayserver.filter.ConsumeGatewayFilter;
import com.github.dge1992.gatewayserver.filter.GatewayRateLimitFilterByIp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

import java.time.Duration;

@ComponentScans(value= {@ComponentScan("com.github.dge1992.commonserver.jwt")})
@SpringBootApplication
public class GatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServerApplication.class, args);
	}

	@Bean
	public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/testConsume")
						.filters(f -> f.filter(new ConsumeGatewayFilter()))
						.uri("http://localhost:8765/testConsume")
						.order(0)
						.id("consume_filter")
				).route(r -> r.path("/testRateLimit")
						.filters(f -> f.filter(new GatewayRateLimitFilterByIp(10,1, Duration.ofSeconds(1))))
						.uri("http://localhost:8765/testRateLimit")
						.id("rateLimit_route")
				)
				.build();
	}
}

