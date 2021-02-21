package com.someexp.handler;

import com.someexp.common.domain.Result;
import com.someexp.common.exception.BusinessException;
import com.someexp.common.exception.ParamsException;
import com.someexp.common.utils.MsgUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局错误处理(这里全返回200的意思是...毕业设计不要出现错误呀(其实是懒前端不想处理这么多)
 *
 * @author someexp
 * @date 2020/9/21
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception e) {

        // http 请求方式错误
        if (e instanceof HttpRequestMethodNotSupportedException) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Result.fail(MsgUtils.get("error.method.not.allowed")));
        }

        // 参数类型错误
        if (e instanceof TypeMismatchException) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Result.fail(MsgUtils.get("error.unsupported.media.type")));
        }

        if (e instanceof HttpMessageNotReadableException) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Result.fail(MsgUtils.get("error.http.message.not.readable.exception")));
        }

        if (e instanceof HttpMediaTypeNotSupportedException) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Result.fail(MsgUtils.get("error.http.media.type.not.supported")));
        }

        // 参数有误
        if (e instanceof ParamsException) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Result.fail(e.getMessage()));
        }

        // 业务错误
        if (e instanceof BusinessException) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Result.fail(e.getMessage()));
        }

        log.error("未知误error: ", e);

        // 未知错误
        return ResponseEntity.status(HttpStatus.OK)
                .body(Result.fail(MsgUtils.get("error.internal.server.error")));
    }

}
