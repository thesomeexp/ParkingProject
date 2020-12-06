package com.someexp.common.exception;

/**
 * 参数异常
 *
 * @author someexp
 * @date 2020/12/1
 */
public class ParamsException extends RuntimeException {

    public ParamsException() {

    }

    public ParamsException(String message) {
        super(message);
    }

}
