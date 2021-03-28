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
     * 状态0未验证1已验证2已锁定
     */
    private Integer status;

}
