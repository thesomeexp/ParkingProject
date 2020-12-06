package com.someexp.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.someexp.config.prop.DataSourceProperties;

import java.sql.SQLException;

/**
 * @author someexp
 * @date 2020/10/23
 */
public class DynamicDataSourceFactory {

    public static DruidDataSource buildDruidDataSource(DataSourceProperties properties) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(properties.getDriverClassName());
        druidDataSource.setUrl(properties.getUrl());
        druidDataSource.setUsername(properties.getUsername());
        druidDataSource.setPassword(properties.getPassword());

        try {
            druidDataSource.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return druidDataSource;
    }

}
