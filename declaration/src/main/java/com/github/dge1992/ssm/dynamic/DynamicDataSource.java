package com.github.dge1992.ssm.dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author  dong
 * @create  2018/1/25 10:05
 * @desc 定义动态数据源
 **/
public class DynamicDataSource extends AbstractRoutingDataSource{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private DataSource defaultTargetDataSource;
    private Map<String, DataSource> targetDataSources;

    public DynamicDataSource(DataSource defaultTargetDataSource, Map<String, DataSource> targetDataSources) {
        this.defaultTargetDataSource = defaultTargetDataSource;
        this.targetDataSources = targetDataSources;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceKey = DynamicDataSourceHolder.getDataSourceKey();
        logger.info("当前的使用的数据库是: " + dataSourceKey);
        return dataSourceKey;
    }

    @Override
    public void afterPropertiesSet() {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(new HashMap<>(targetDataSources));
        super.afterPropertiesSet();
    }
}
