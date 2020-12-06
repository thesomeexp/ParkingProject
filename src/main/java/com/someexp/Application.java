package com.someexp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author someexp
 * @date 2020/9/21
 */
@SpringBootApplication
@MapperScan(basePackages = "com.someexp.modules.*.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
