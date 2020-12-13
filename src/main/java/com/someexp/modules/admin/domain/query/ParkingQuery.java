package com.someexp.modules.admin.domain.query;

import com.someexp.common.domain.PageParamQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author someexp
 * @date 2020/12/13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ParkingQuery extends PageParamQuery {

    private String name;

    private Integer status;

}
