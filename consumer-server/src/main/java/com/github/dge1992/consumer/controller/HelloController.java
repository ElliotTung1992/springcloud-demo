package com.github.dge1992.consumer.controller;

import brave.propagation.ExtraFieldPropagation;
import com.github.dge1992.common.domain.User;
import com.github.dge1992.consumer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EurekaClientConfigBean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

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

    @Autowired
    private RestTemplate restTemplate;

    /**
     * @author 董感恩
     * @date 2020-02-09 15:59:24
     * @desc 测试使用RestTemplate进行服务访问
     **/
    @GetMapping("/testRestTemplate")
    public Object testRestTemplate(@RequestParam("name")String name){
        String url = "http://localhost:9762/hi?name="+name;
        return restTemplate.getForObject(url,String.class);
    }

    @Autowired
    private ExecutorService executorService;

    /**
     *
     * @author 董感恩
     * @date 2020-02-09 16:03:05
     * @desc executorService进行服务访问
     **/
    @GetMapping("/testExecutorService")
    public Object testExecutorService(@RequestParam("name")String name) throws ExecutionException, InterruptedException {
        Future future = executorService.submit(() -> {
            String result = helloService.hello(name);
            return result;
        });
        return future.get();
    }

    /**
     * @author 董感恩
     * @date 2020-02-09 16:48:27
     * @desc 测试session拦截器
     **/
    @GetMapping(value = "/testSessionFilter")
    public String testSessionFilter() {
        ExtraFieldPropagation.set("sessionId", "dge");
        return helloService.testSessionFilter();
    }
}
