package com.someexp.modules.user.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author someexp
 * @date 2020/12/16
 */
@Data
public class ParkingVO implements Serializable {

    private Long id;

    /**
     * 创建的用户id
     */
    private Long uid;

    /**
     * 停车场名称
     */
    private String name;

    /**
     * 停车场描述
     */
    private String content;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 图片链接
     */
    private String firstUrl;

    /**
     * 拥挤度数据更新时间
     */
    private LocalDateTime stateUpdateDate;

    private Integer t0;

    private Integer t1;

    private Integer t2;

    private Integer t3;

    private Integer t4;

    private Integer t5;

    private Integer t6;

    private Integer t7;

    private Integer t8;

    private Integer t9;

    private Integer t10;

    private Integer t11;

    private Integer t12;

    private Integer t13;

    private Integer t14;

    private Integer t15;

    private Integer t16;

    private Integer t17;

    private Integer t18;

    private Integer t19;

    private Integer t20;

    private Integer t21;

    private Integer t22;

    private Integer t23;

    private Double distance;
}