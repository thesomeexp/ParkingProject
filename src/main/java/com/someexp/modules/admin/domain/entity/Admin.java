package com.someexp.modules.admin.domain.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Admin implements Serializable {
    private Long id;

    /**
     * 父级账号id
     */
    private Long parent;

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
