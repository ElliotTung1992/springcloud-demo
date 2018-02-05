package com.github.dge1992.logaop.controller;

import com.github.dge1992.logaop.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("testLog")
    @ResponseBody
    public void testLog(String name, int age){
        testService.TestLog(name, age);
    }
}
