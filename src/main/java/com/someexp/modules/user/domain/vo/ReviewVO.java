package com.someexp.modules.user.domain.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ReviewVO {

    private Long id;

    private Long pid;

    private Long uid;

    private Integer score;

    private String content;

    private Date createdDate;

    private String parkingName;

}
