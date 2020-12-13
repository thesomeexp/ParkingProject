package com.someexp.common.utils;

/**
 * @author someexp
 * @date 2020/12/13
 */
public class BeanUtils {

    public static void copyProperties(Object source, Object target) {
        org.springframework.beans.BeanUtils.copyProperties(source, target);
    }

}
