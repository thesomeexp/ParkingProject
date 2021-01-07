package com.someexp.modules.user.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author someexp
 * @date 2020/9/22
 */
@Data
public class User implements Serializable {
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 角色
     */
    private String role;

    /**
     * 状态0未验证1已验证2已锁定
     */
    private Long status;

}
