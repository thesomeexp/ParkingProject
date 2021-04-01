package com.someexp.modules.admin.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author someexp
 * @Date 2021/4/1
 */
@Data
public class AdminVO implements Serializable {
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
     * 手机号
     */
    private String phone;

    /**
     * 状态0未验证1已验证2已锁定
     */
    private Integer status;

}
