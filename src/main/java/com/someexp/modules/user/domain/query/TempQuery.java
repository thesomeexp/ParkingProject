package com.someexp.modules.user.domain.query;

import com.someexp.common.validator.group.QueryGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author someexp
 * @date 2020/12/31
 */
@Data
public class TempQuery {

    /**
     * 停车场id
     */
    @NotNull(message = "temp.pid.cant.be.null", groups = QueryGroup.class)
    private Long pid;

}
