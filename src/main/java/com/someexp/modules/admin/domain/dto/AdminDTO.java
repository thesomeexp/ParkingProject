package com.someexp.modules.admin.domain.dto;

import com.someexp.common.validator.group.LoginGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author someexp
 * @date 2020/12/6
 */
@Data
public class AdminDTO {

    /**
     * 手机号
     */
    @NotBlank(message = "admin.phone.cant.be.null", groups = LoginGroup.class)
    private String phone;

    /**
     * 密码
     */
    @NotBlank(message = "admin.password.cant.be.null", groups = LoginGroup.class)
    private String password;

}
