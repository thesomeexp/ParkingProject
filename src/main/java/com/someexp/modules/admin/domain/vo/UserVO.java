package com.someexp.modules.admin.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author someexp
 * @Date 2021/4/18
 */
@Data
public class UserVO implements Serializable {
    private Long id;

    /**
     * 用户名
     */
    private String name;

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
