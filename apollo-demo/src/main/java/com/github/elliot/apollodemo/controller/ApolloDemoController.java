package com.github.elliot.apollodemo.controller;

import com.github.elliot.apollodemo.doamin.CarConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/apollo-config")
@RestController
public class ApolloDemoController {

    @Autowired
    private CarConfig carConfig;

    @GetMapping("/getCarName")
    public String test(){
        return carConfig.getName();
    }
}
