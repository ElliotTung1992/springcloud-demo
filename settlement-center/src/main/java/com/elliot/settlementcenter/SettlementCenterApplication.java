package com.elliot.settlementcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * 库存服务
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SettlementCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SettlementCenterApplication.class, args);
    }

}
