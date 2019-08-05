package com.github.dge1992.consulprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@RestController
@SpringBootApplication
public class ConsulProviderApplication {

    @GetMapping("/hi")
    public String home() {
        return "hi ,i'm consul-provider";
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsulProviderApplication.class, args);
    }

}
