package com.github.trang.dynamic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author  dong
 * @create  2018/1/26 14:05
 * @desc 注解式声明 完成读写分离
 *
 *  原作者github连接地址：https://github.com/drtrang/dynamic-data-source-demo
 *
 **/
@SpringBootApplication
@Slf4j
public class AnnotationalApplication {

    public static void main(String[] args) {
        System.setProperty("druid.logType", "slf4j");
        SpringApplication.run(AnnotationalApplication.class, args);
        log.info("dynamic-data-source-demo is running...");
    }

}