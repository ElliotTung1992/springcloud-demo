package com.github.dge1992.client.controller;

import com.github.dge1992.client.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/7/16
 **/
@RestController
public class TestController {

    @Autowired
    HelloService helloService;

    @GetMapping(value = "/hello")
    public String hello(@RequestParam String name) {
        return helloService.helloProvider( name );
    }

    @GetMapping(value = "/zuul")
    public String zuul() {
        return "我是ribbon-server的zuul";
    }

    @RequestMapping("/hi")
    public String hi(){
        return "i am ribbon";
    }
}
