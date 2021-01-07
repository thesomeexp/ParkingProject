package com.someexp.modules.user.domain.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author someexp
 * @date 2021/1/7
 */
@Data
public class Review {

    private Long id;

    private Long pid;

    private Long uid;

    private Integer score;

    private String content;

    private Date createdDate;

}
