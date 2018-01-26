package com.github.dge1992.ssm.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.github.dge1992.ssm.dynamic.DynamicDataSource;
import com.github.trang.druid.datasource.DruidMultiDataSource;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.Map;


/**
 * @author  dong
 * @create  2018/1/25 10:22
 * @desc 动态数据源配置
 **/
@Configuration
public class SpringDataSourceConfig {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String MASTER_DATA_SOURCE_PREFIX = "spring.datasource.druid.master";
    private static final String SLAVE_DATA_SOURCE_PREFIX = "spring.datasource.druid.slave";

    @Bean
    @ConfigurationProperties(MASTER_DATA_SOURCE_PREFIX)
    public DruidDataSource masterDataSource(){
        logger.info("------ 初始化 Druid 主数据源 ------");
        return new DruidMultiDataSource();
    }

    @Bean(value="slaveDataSource", initMethod = "init", destroyMethod = "close")
    @ConfigurationProperties(SLAVE_DATA_SOURCE_PREFIX)
    public DruidDataSource slaveDataSource() {
        logger.info("------ 初始化 Druid 从数据源 ------");
        return new DruidMultiDataSource();
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(DruidDataSource masterDataSource, DruidDataSource slaveDataSource) {
        logger.info("------ 初始化 Dynamic 数据源 ------");
        Map<String, DataSource> targetDataSources = ImmutableMap.<String, DataSource>builder()
                .put("master", masterDataSource)
                .put("slave", slaveDataSource)
                .build();
        return new DynamicDataSource(slaveDataSource, targetDataSources);
    }
}
