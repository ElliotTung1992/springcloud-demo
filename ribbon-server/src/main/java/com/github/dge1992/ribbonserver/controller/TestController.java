package com.github.dge1992.ribbonserver.controller;

import com.github.dge1992.ribbonserver.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping(value = "/hi")
    public String hi(@RequestParam String name) {
        return helloService.hiService( name );
    }

    @GetMapping(value = "/zuul")
    public String zuul() {
        return "我是ribbon-server的zuul";
    }
}
