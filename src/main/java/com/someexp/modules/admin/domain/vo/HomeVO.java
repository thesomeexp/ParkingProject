package com.someexp.modules.admin.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author someexp
 * @date 2020/12/7
 */
@Data
public class HomeVO implements Serializable {

    /**
     * 总数量
     */
    private Long sum;

    /**
     * 已验证数量
     */
    private Long verified;

    /**
     * 未验证数量
     */
    private Long unverified;

    /**
     * 已禁用
     */
    private Long disabled;

}
