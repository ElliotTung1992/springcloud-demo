package com.github.dge1992.consumer.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 董感恩
 * @date 2020-02-03 11:25
 * @desc 网关控制器
 */
@Api("测试集成Swagger")
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
    public String testHystrix(){
        try {
            Thread.sleep(10000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "testHystrix is ok";
    }

    /**
     * @author 董感恩
     * @date 2020-02-03 21:20:08
     * @desc 测试处理时长
     **/
    @GetMapping("/testConsume")
    public Object testConsume(){
        try {
            Thread.sleep(200l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "请求成功！";
    }

    /**
     * @author 董感恩
     * @date 2020-02-03 22:07:52
     * @desc 权重v1
     **/
    @GetMapping("/v1")
    public Object v1(){
        return "v1";
    }

    /**
     * @author 董感恩
     * @date 2020-02-03 22:07:52
     * @desc 权重v2
     **/
    @GetMapping("/v2")
    public Object v2(){
        return "v2";
    }

    /**
     * @author 董感恩
     * @date 2020-02-04 10:29:21
     * @desc 测试网关和swagger集成
     **/
    @ApiOperation(value = "计算+", notes = "加法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "a",  value = "数字a", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "b",  value = "数字b", required = true, dataType = "Long")
    })
    @GetMapping("/test/{a}/{b}")
    public Object testSwagger(@PathVariable Integer a, @PathVariable Integer b){
        return a + b;
    }

    /**
     * @author 董感恩
     * @date 2020-02-04 12:07:11
     * @desc 测试限流
     **/
    @GetMapping("/testRateLimit")
    public Object testRateLimit(){
        return "testRateLimit is ok";
    }

    /**
     * @author 董感恩
     * @date 2020-02-04 12:07:11
     * @desc redis限流
     **/
    @GetMapping("/testRedisRateLimit")
    public Object testRedisRateLimit(@RequestParam("name") String name){
        return "testRedisRateLimit is ok name is " + name;
    }
}
