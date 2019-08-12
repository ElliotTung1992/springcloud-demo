package com.github.dge1992.feignserver.controller;

import com.github.dge1992.feignserver.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EurekaClientConfigBean;
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

    @GetMapping(value = "/helloRibbon")
    public String helloRibbon(@RequestParam String name) {
        return helloService.helloRibbon(name);
    }

    @GetMapping(value = "/zuul")
    public String zuul() {
        return "我是feign-server的zuul";
    }

    @Autowired
    EurekaClientConfigBean eurekaClientConfigBean;

    //获取eurekaServer列表
    @GetMapping("/getEurekaServers")
    public Object getEurekaServers(){
        return eurekaClientConfigBean.getServiceUrl();
    }
}
