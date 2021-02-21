package com.someexp.modules.sys.domain.entity;

import lombok.Data;

@Data
public class ShiroUser {
    private Long id;

    /**
     * 父级账号id
     */
    private Long parent;

    /**
     * 角色
     */
    private String role;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机区号
     */
    private Integer areaCode;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 状态0未验证1已验证2已锁定
     */
    private Integer status;

}
