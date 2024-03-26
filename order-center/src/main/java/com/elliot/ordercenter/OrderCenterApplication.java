package com.elliot.ordercenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 订单服务
 */
@EnableFeignClients(basePackages = "com.elliot.ordercenter.service.external")
@SpringBootApplication
@EnableDiscoveryClient
public class OrderCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderCenterApplication.class, args);
    }

}
