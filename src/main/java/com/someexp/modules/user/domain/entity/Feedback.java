package com.someexp.modules.user.domain.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author someexp
 * @date 2021/3/27
 */
@Data
public class Feedback {

    private Long id;

    private Long pid;

    private Long uid;

    private Date createdDate;

    private String content;

    private Integer status;

    private String contact;

}
