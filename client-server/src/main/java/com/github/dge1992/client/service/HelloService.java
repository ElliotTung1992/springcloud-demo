package com.github.dge1992.client.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/7/16
 **/
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiError")
    public String helloProvider(String name) {
        return restTemplate.getForObject("http://consumer-server/hello?name="+name,String.class);
    }

    public String hiError(String name) {
        return "client-server服务的hiService接口挂了!!!";
    }
}
