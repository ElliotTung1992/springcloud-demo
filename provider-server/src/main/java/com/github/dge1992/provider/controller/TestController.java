package com.github.dge1992.provider.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author 小眼睛带鱼
 * @Description 测试接口
 * @Date 2019/7/23
 **/
@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.port}")
    private Integer port;

    @HystrixCommand(fallbackMethod = "hiError")
    @GetMapping("/hi")
    public String hi(@RequestParam(value = "name", defaultValue = "小眼睛带鱼") String name) {
        //int i = 10/0;
        return "hi I am provider-server my name is " + name + " my port is " + port;
    }

    @RequestMapping("/helloRibbon")
    public String helloRibbon(){
        return restTemplate.getForObject("http://ribbon-server/hi", String.class);
    }

    public String hiError(String name) {
        return "service-hi服务的hi接口挂了!!!";
    }
}
