package com.github.elliot.apollodemo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;

@ImportAutoConfiguration(RefreshAutoConfiguration.class)
@EnableApolloConfig
@SpringBootApplication
public class ApolloDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApolloDemoApplication.class, args);
    }

}
