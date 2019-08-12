package com.github.dge1992.eureka2server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class Eureka2ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Eureka2ServerApplication.class, args);
    }

}
