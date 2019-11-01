package com.github.dge1992.consumer.service;

import com.github.dge1992.common.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-hi", fallback = HelloServiceHystrix.class)
public interface HelloService {

    @GetMapping(value = "/hi")
    String hello(@RequestParam(value = "name") String name);

    @GetMapping("/testGetPojo")
    Object testGetPojo(User user);
}
