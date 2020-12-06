package com.someexp.common.exception;

/**
 * 业务异常类
 * @author someexp
 * @date 2020/9/21
 */
public class BusinessException extends RuntimeException {

    public BusinessException() {

    }

    public BusinessException(String message) {
        super(message);
    }

}
