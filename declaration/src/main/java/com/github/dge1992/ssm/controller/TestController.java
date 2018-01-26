package com.github.dge1992.ssm.controller;

import com.github.dge1992.ssm.domain.Cat;
import com.github.dge1992.ssm.dynamic.DynamicDataSourceHolder;
import com.github.dge1992.ssm.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/sayHello")
    @ResponseBody
    public Object sayHello(){
        return "hello spring boot";
    }

    @RequestMapping("/queryCat")
    @ResponseBody
    public Object queryCat(){
        DynamicDataSourceHolder.markMaster();
        List<Cat> cats = testService.queryCat();
        return cats;
    }

    @RequestMapping("/insertCat")
    @ResponseBody
    public Object insertCat(){
        DynamicDataSourceHolder.markMaster();
        Cat cat = new Cat();
        cat.setId(2);
        cat.setName("mimi");
        testService.insertCat(cat);
        return "success";
    }
}
