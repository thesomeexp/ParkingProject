package com.someexp.common.enums;

/**
 * @author someexp
 * @date 2020/10/28
 */
public enum ErrorCode {

    /**
     * 成功
     */
    SUCCESS(0),

    /**
     * 失败
     */
    FAIL(1);

    private final int value;

    ErrorCode(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }

}