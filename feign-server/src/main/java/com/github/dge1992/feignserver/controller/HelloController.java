package com.github.dge1992.feignserver.controller;

import com.github.dge1992.common.domain.User;
import com.github.dge1992.feignserver.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EurekaClientConfigBean;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/7/16
 **/
@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    @GetMapping(value = "/hello")
    public String hello(@RequestParam String name) {
        return helloService.hello(name);
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

    @GetMapping("/testGetPojo")
    public Object testGetPojo(User user){
        return helloService.testGetPojo(user);
    }
}
