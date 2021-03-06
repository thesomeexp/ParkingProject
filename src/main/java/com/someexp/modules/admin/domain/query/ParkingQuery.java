package com.someexp.modules.admin.domain.query;

import com.someexp.common.domain.PageParamQuery;
import com.someexp.common.validator.group.QueryGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author someexp
 * @date 2020/12/13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ParkingQuery extends PageParamQuery {

    private String name;

    @NotNull(message = "parking.status.cant.be.null", groups = QueryGroup.class)
    private Integer status;

}
