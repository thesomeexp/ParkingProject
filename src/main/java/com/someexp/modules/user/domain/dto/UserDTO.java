package com.someexp.modules.user.domain.dto;

import com.someexp.common.validator.group.LoginGroup;
import com.someexp.common.validator.group.RegisterGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author someexp
 * @date 2020/9/23
 */
@Data
public class UserDTO {

    /**
     * 用户名
     */
    @NotBlank(message = "user.name.cant.be.null", groups = RegisterGroup.class)
    private String name;

    /**
     * 手机号
     */
    @NotBlank(message = "user.phone.cant.be.null", groups = RegisterGroup.class)
    @NotBlank(message = "user.phone.cant.be.null", groups = LoginGroup.class)
    private String phone;

    /**
     * 密码
     */
    @NotBlank(message = "user.password.cant.be.null", groups = RegisterGroup.class)
    @NotBlank(message = "user.password.cant.be.null", groups = LoginGroup.class)
    private String password;

}
