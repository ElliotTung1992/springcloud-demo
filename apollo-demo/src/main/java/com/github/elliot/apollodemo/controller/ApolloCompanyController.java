package com.github.elliot.apollodemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RequestMapping("/apollo-config-company")
@RestController
public class ApolloCompanyController {

    @Value("${company.name}")
    private String name;

    @GetMapping("/getCompanyName")
    public String test(){
        return name;
    }
}
