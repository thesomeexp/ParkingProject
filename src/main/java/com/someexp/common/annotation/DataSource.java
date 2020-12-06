package com.someexp.common.annotation;

import java.lang.annotation.*;

/**
 * 动态切换数据源配置
 * @author someexp
 * @date 2020/10/23
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource {
    String value() default "";
}
