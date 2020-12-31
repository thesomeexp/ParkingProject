package com.someexp.modules.user.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author someexp
 * @date 2020/12/31
 */
@Data
public class Temp implements Serializable {

    private Long id;

    private Long pid;

    private Long uid;

    private Integer state;

    private Integer useful;

    private Integer unuseful;

    private Date stateUpdateDate;

}
