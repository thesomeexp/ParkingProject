package com.someexp.common.utils;

import com.someexp.modules.sys.domain.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @author someexp
 * @date 2020/10/26
 */
public class ShiroUtils {

    public static User getUser() {
        return (User) SecurityUtils.getSubject().getPrincipal();
    }

    public static Long getUserId() {
        return getUser().getId();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

}
