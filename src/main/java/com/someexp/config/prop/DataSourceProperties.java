package com.someexp.config.prop;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author someexp
 * @date 2020/9/28
 */
@Data
public class DataSourceProperties {

    private String driverClassName;

    private String url;

    private String username;

    private String password;

}
