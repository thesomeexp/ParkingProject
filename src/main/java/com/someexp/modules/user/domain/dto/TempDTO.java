package com.someexp.modules.user.domain.dto;

import com.someexp.common.validator.group.AddGroup;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author someexp
 * @date 2020/12/31
 */
@Data
public class TempDTO {

    /**
     * 停车场id
     */
    @NotNull(message = "temp.pid.cant.be.null", groups = AddGroup.class)
    private Long pid;

    /**
     * 拥挤度
     */
    @NotNull(message = "temp.state.cant.be.null", groups = AddGroup.class)
    @Min(value = 0, message = "temp.state.out.of.min", groups = AddGroup.class)
    @Max(value = 10, message = "temp.state.out.of.max", groups = AddGroup.class)
    private Integer state;

}
