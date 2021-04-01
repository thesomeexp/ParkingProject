package com.someexp.modules.admin.domain.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author someexp
 * @Date 2021/4/1
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
