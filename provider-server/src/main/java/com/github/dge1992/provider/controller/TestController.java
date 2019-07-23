package com.github.dge1992.provider.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 小眼睛带鱼
 * @Description 测试接口
 * @Date 2019/7/23
 **/
@RestController
public class TestController {

    @Value("${server.port}")
    private Integer port;

    @HystrixCommand(fallbackMethod = "hiError")
    @GetMapping("/hi")
    public String hi(@RequestParam(value = "name", defaultValue = "小眼睛带鱼") String name) {
        int i = 1/0;
        return "hi I am " + name + " my port is " + port;
    }

    public String hiError(String name) {
        return "service-hi服务的hi接口挂了!!!";
    }
}
