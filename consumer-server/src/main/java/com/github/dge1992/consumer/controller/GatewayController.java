package com.github.dge1992.consumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 董感恩
 * @date 2020-02-03 11:25
 * @desc 网关控制器
 */
@RestController
public class GatewayController {

    /**
     * @author 董感恩
     * @date 2020-02-03 11:30:22
     * @desc 测试网关添加Head过滤器
     **/
    @GetMapping("/testAddRequestHeader")
    public Object testAddRequestHeader(HttpServletRequest request){
        return request.getHeader("X-Request-Acme");
    }
    
    /**
     * @author 董感恩
     * @date 2020-02-03 11:31:35
     * @desc 测试网关添加Param过滤器
     **/
    @GetMapping("/testAddRequestParameter")
    public Object testAddRequestParameter(@RequestParam("name") String name){
        return name;
    }

    /**
     * @author 董感恩
     * @date 2020-02-03 12:30:55
     * @desc 测试网关重写路径过滤器
     **/
    @GetMapping("/bb/cc")
    public Object testRewritePath(){
        return "/bb/cc";
    }

    /**
     * @author 董感恩
     * @date 2020-02-03 12:49:09
     * @desc 测试添加Response过滤器
     **/
    @GetMapping("/testAddResponseHeader")
    public Object testAddResponseHeader(){
        return "ok";
    }

    /**
     * @author 董感恩
     * @date 2020-02-03 13:00:48
     * @desc 测试重试过滤器
     **/
    @GetMapping("/testRetry")
    public Object testRetry(){
        System.out.println("重试错误");
        throw new RuntimeException("重试错误！！！");
    }

    /**
     * @author 董感恩
     * @date 2020-02-03 13:36:20
     * @desc 测试熔断过滤器
     **/
    @GetMapping("/testHystrix")
    public  String testHystrix(){
        try {
            Thread.sleep(10000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "testHystrix is ok";
    }
}
