package com.someexp.modules.user.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author someexp
 * @date 2021/1/5
 */
@Data
public class TempVO implements Serializable {

    private Long id;

    private Long pid;

    private Long uid;

    private Integer state;

    private Integer useful;

    private Integer unuseful;

    private Date stateUpdateDate;

    private Integer myOpt;

}
