package com.someexp.modules.user.domain.query;

import com.someexp.common.validator.group.QueryGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author someexp
 * @date 2020/12/16
 */
@Data
public class ParkingQuery {

    /**
     * 坐标
     */
    @NotBlank(message = "user.location.cant.be.null", groups = QueryGroup.class)
    private String location;

}
