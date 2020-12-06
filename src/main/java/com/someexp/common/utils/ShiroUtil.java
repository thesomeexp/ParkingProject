package com.someexp.common.utils;

import com.someexp.modules.user.domain.entity.User;
import org.apache.shiro.SecurityUtils;

/**
 * @author someexp
 * @date 2020/10/26
 */
public class ShiroUtil {

    public static User getUser() {
        return (User) SecurityUtils.getSubject().getPrincipal();
    }

    public static Long getUserId() {
        return getUser().getId();
    }

}
