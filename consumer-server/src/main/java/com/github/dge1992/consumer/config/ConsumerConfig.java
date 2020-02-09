package com.github.dge1992.consumer.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.instrument.async.TraceableExecutorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author 董感恩
 * @date 2020-02-09 15:36:59
 * @desc 用于注册RestTemplate和ExecutorService线程池
 **/
@Configuration
public class ConsumerConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Autowired
    BeanFactory beanFactory;

    @Bean
    public ExecutorService executorService(){
        ExecutorService executorService =  Executors.newFixedThreadPool(2);
        return new TraceableExecutorService(this.beanFactory, executorService);
    }
}
