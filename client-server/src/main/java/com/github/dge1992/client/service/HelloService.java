package com.github.dge1992.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/7/16
 **/
@FeignClient(value = "CONSUMER-SERVER")
public interface HelloService {

    @GetMapping(value = "/hello")
    String helloProvider(@RequestParam("name") String name);
}
