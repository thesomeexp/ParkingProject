package com.someexp.config.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 配置化属性
 *
 * @author someexp
 * @date 2020/9/23
 */
@Configuration
@ConfigurationProperties(prefix = "someexp.jwt")
@EnableConfigurationProperties
@Data
public class JwtProperties {

    private String header;

    private String jwtPrefix;

    private String secret;

    private Long expire;

}
