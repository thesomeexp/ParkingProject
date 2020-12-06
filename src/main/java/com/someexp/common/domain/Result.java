package com.someexp.common.domain;

import com.someexp.common.enums.ErrorCode;
import lombok.Data;

/**
 * 统一返回结果
 *
 * @author someexp
 * @date 2020/9/21
 */
@Data
public class Result {

    /**
     * 自定义错误码
     */
    private int code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应数据
     */
    private Object data;

    public Result(ErrorCode code, String msg, Object data) {
        this.code = code.value();
        this.msg = msg;
        this.data = data;
    }

    public static Result success(Object data, String msg) {
        return new Result(ErrorCode.SUCCESS, msg, data);
    }

    public static Result fail(String msg) {
        return new Result(ErrorCode.FAIL, msg, null);
    }

    public static Result fail(ErrorCode code, String msg) {
        return new Result(code, msg, null);
    }

}
