package com.github.dge1992.feignserver.controller;

import com.github.dge1992.feignserver.service.HelloService;
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
public class HelloController {

    @Autowired
    HelloService helloService;

    @GetMapping(value = "/hi")
    public String sayHi(@RequestParam String name) {
        return helloService.hello( name );
    }
}
