package com.someexp.modules.user.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * UserVO 不允许出现敏感信息
 *
 * @author someexp
 * @date 2020/12/1
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
