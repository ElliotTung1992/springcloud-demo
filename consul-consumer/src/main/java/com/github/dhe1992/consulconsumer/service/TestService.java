package com.github.dhe1992.consulconsumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/8/5
 **/
@FeignClient(value = "consul-provider")
public interface TestService {

    @GetMapping("/hi")
    void hi();
}
