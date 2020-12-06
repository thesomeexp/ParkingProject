package com.someexp.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

/**
 * @author someexp
 * @date 2020/10/24
 */
@Component
public class MsgUtil {

    private static MessageSourceAccessor messageSourceAccessor;

    @Autowired
    public MsgUtil(MessageSourceAccessor messageSourceAccessor) {
        MsgUtil.messageSourceAccessor = messageSourceAccessor;
    }

    public static String get(String code) {
        return messageSourceAccessor.getMessage(code);
    }

    public static String get(String code, Object[] args) {
        return messageSourceAccessor.getMessage(code, args);
    }

}
