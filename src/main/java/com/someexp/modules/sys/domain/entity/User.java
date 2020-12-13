package com.someexp.modules.sys.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 只作为Shiro权限验证时使用
 *
 * @author someexp
 * @date 2020/12/9
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
     * 积分
     */
    private Long score;

    /**
     * 状态0未验证1已验证2已锁定
     */
    private Long status;

    /**
     * 有价值数量
     */
    private Long useful;

    /**
     * 无价值数量
     */
    private Long unuseful;

}
