package com.github.dge1992.ssm.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan("com.github.dge1992.ssm.dao")
@EnableTransactionManagement(proxyTargetClass = true)
public class SpringDaoConfig {

}
