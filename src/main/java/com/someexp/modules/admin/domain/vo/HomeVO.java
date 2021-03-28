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
     * 已验证停车场
     */
    private Integer parkingVerified = 0;

    /**
     * 未验证停车场
     */
    private Integer parkingUnverified = 0;

    /**
     * 已禁用停车场
     */
    private Integer parkingDisabled = 0;

    /**
     * 未处理反馈
     */
    private Integer feedbackUntreated = 0;

    /**
     * 已处理反馈
     */
    private Integer feedbackFinished = 0;

}
