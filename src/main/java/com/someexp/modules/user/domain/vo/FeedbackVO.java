package com.someexp.modules.user.domain.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author someexp
 * @Date 2021/3/27
 */
@Data
public class FeedbackVO {

    private Long id;

    private Long pid;

    private Long uid;

    private Date createdDate;

    private String content;

    private Integer status;

    private String contact;

    private String parkingName;

}
