package com.someexp.modules.user.domain.dto;

import com.someexp.common.validator.group.AddGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author someexp
 * @date 2021/1/5
 */
@Data
public class TempUserDTO {

    /**
     * Temp的id
     */
    @NotNull(message = "tempUser.tid.cant.be.null", groups = AddGroup.class)
    private Long tid;

    /**
     * 用户选择的状态
     */
    @NotNull(message = "tempUser.opt.cant.be.null", groups = AddGroup.class)
    private Integer opt;

}
