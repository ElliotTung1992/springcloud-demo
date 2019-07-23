package com.github.dge1992.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker
public class ProviderApplication {

    //http://localhost:9763/actuator/hystrix.stream
    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }
}
