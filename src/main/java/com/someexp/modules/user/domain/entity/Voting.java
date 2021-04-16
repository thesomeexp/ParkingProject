package com.someexp.modules.user.domain.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author someexp
 * @date 2021/1/5
 */
@Data
public class Voting {

    private Long id;

    private Long uid;

    private Long tid;

    private Integer useful;

    private Date createDate;

}
