package com.someexp.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.someexp.config.datasource.DynamicDataSource;
import com.someexp.config.datasource.DynamicDataSourceFactory;
import com.someexp.config.prop.DataSourceProperties;
import com.someexp.config.prop.DynamicDataSourceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 动态数据源的配置
 *
 * @author someexp
 * @date 2020/10/23
 */
//@Configuration
public class DynamicDataSourceConfig {

    @Autowired
    private DynamicDataSourceProperties properties;

    @Bean
    public DynamicDataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> allDynamicDataSource = getDynamicDataSource();

        DruidDataSource primaryDataSource = (DruidDataSource) allDynamicDataSource.get("primary");
        dynamicDataSource.setTargetDataSources(allDynamicDataSource);

        //默认数据源为主数据源
        dynamicDataSource.setDefaultTargetDataSource(primaryDataSource);

        return dynamicDataSource;
    }

    private Map<Object, Object> getDynamicDataSource() {
        Map<String, DataSourceProperties> dataSourcePropertiesMap = properties.getDatasource();
        Map<Object, Object> targetDataSources = new HashMap<>(dataSourcePropertiesMap.size());
        dataSourcePropertiesMap.forEach((k, v) -> {
            DruidDataSource druidDataSource = DynamicDataSourceFactory.buildDruidDataSource(v);
            targetDataSources.put(k, druidDataSource);
        });

        return targetDataSources;
    }

}
