package com.github.dge1992.gatewayserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 董感恩
 * @date 2020-02-03 13:49
 * @desc 网关熔断回调控制器
 */
@RestController
public class HystrixController {

    /**
     * @author 董感恩
     * @date 2020-02-03 13:34:14
     * @desc 熔断后请求跳转
     **/
    @GetMapping("/hystrix")
    public String hystrix(){
        return "spring-cloud-gateway fallback！！";
    }
}
