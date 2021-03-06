package com.someexp.modules.user.domain.dto;

import com.someexp.common.validator.group.AddGroup;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author someexp
 * @date 2021/1/5
 */
@Data
public class VotingDTO {

    /**
     * Temp的id
     */
    @NotNull(message = "voting.tid.cant.be.null", groups = AddGroup.class)
    private Long tid;

    /**
     * 用户选择的状态
     */
    @NotNull(message = "voting.useful.cant.be.null", groups = AddGroup.class)
    @Min(value = -1, message = "voting.useful.out.of.min", groups = AddGroup.class)
    @Max(value = 1, message = "voting.useful.out.of.max", groups = AddGroup.class)
    private Integer useful;

}
