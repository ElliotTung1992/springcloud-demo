package com.github.dhe1992.consulconsumer.controller;

import com.github.dhe1992.consulconsumer.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/8/5
 **/
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/test")
    public Object test(){
        testService.hi();
        return "调用成功！！！";
    }
}
