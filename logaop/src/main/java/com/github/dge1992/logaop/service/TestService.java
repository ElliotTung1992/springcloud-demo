package com.github.dge1992.logaop.service;

import com.github.dge1992.logaop.annotion.BussinessLog;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @BussinessLog
    public void TestLog(String name, int age){
        System.out.println("TestLog");
    }
}
