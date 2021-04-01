package com.someexp.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author someexp
 * @date 2020/12/2
 */
@Component
public class PasswordEncoderUtils {

    private static BCryptPasswordEncoder ENCODER;

    @Autowired
    public PasswordEncoderUtils(BCryptPasswordEncoder bCryptPasswordEncoder) {
        PasswordEncoderUtils.ENCODER = bCryptPasswordEncoder;
    }

    /**
     * 返回密码哈希
     *
     * @param rawPassword
     * @return
     */
    public static String encode(CharSequence rawPassword) {
        return ENCODER.encode(rawPassword);
    }

    /**
     * 匹配密码
     *
     * @param rawPassword
     * @param encodedPassword
     * @return
     */
    public static boolean matches(CharSequence rawPassword, String encodedPassword) {
        return ENCODER.matches(rawPassword, encodedPassword);
    }

}
