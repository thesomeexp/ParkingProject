package com.someexp.modules.admin.domain.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author someexp
 * @Date 2021/3/31
 */
@Data
public class Review {

    private Long id;

    private Long pid;

    private Long uid;

    private Long score;

    private String content;

    private Date createDate;

}
