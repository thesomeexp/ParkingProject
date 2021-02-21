package com.someexp.common.utils;

import com.someexp.modules.sys.domain.entity.ShiroUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @author someexp
 * @date 2020/10/26
 */
public class ShiroUtils {

    public static ShiroUser getShiroUser() {
        return (ShiroUser) SecurityUtils.getSubject().getPrincipal();
    }

    public static Long getUserId() {
        return getShiroUser().getId();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

}
