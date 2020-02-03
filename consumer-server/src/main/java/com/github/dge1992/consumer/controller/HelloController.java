package com.github.dge1992.consumer.controller;

import com.github.dge1992.common.domain.User;
import com.github.dge1992.consumer.service.HelloService;
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

    @GetMapping(value = "/hello/test/gateway")
    public String testGateway() {
        return "我是/hello/test/gateway";
    }

    @GetMapping(value = "/gateway")
    public String gateway() {
        return "我是/gateway";
    }

    @GetMapping("/testPathVariable/{name}")
    public String testPathVariable(@PathVariable("name") String name){
        return "测试路径变量！！！" + name;
    }

    @GetMapping("/user")
    public String user(@RequestParam("id")Integer id){
        return String.valueOf(id);
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
