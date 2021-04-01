package com.someexp.modules.admin.domain.dto;

import com.someexp.common.validator.group.AddGroup;
import com.someexp.common.validator.group.Ban;
import com.someexp.common.validator.group.LoginGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author someexp
 * @date 2020/12/6
 */
@Data
public class AdminDTO {

    @NotNull(message = "admin.id.cant.be.null", groups = Ban.class)
    private Long id;

    @NotNull(message = "admin.relate.cant.be.null", groups = Ban.class)
    private Boolean isRelated;

    @NotNull(message = "admin.status.cant.be.null", groups = Ban.class)
    private Integer status;

    @NotBlank(message = "admin.name.cant.be.null", groups = AddGroup.class)
    private String name;

    /**
     * 手机号
     */
    @NotBlank(message = "admin.phone.cant.be.null", groups = LoginGroup.class)
    @NotBlank(message = "admin.phone.cant.be.null", groups = AddGroup.class)
    private String phone;

    /**
     * 密码
     */
    @NotBlank(message = "admin.password.cant.be.null", groups = LoginGroup.class)
    @NotBlank(message = "admin.password.cant.be.null", groups = AddGroup.class)
    private String password;

}
