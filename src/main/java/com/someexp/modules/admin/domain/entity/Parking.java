package com.someexp.modules.admin.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author someexp
 * @date 2020/12/9
 */
@Data
public class Parking implements Serializable {

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
     * GeoHash
     */
    private String geohash;

    /**
     * 状态:0.未验证1.已验证2.已禁用
     */
    private Integer status;

    /**
     * 拥挤度数据更新时间
     */
    private Date stateUpdateDate;

    private Double t0;

    private Double t1;

    private Double t2;

    private Double t3;

    private Double t4;

    private Double t5;

    private Double t6;

    private Double t7;

    private Double t8;

    private Double t9;

    private Double t10;

    private Double t11;

    private Double t12;

    private Double t13;

    private Double t14;

    private Double t15;

    private Double t16;

    private Double t17;

    private Double t18;

    private Double t19;

    private Double t20;

    private Double t21;

    private Double t22;

    private Double t23;

    private Integer capacity;

}