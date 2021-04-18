package com.someexp.modules.admin.domain.dto;

import com.someexp.common.validator.group.UpdateGroup;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author someexp
 * @Date 2021/4/18
 */
@Data
public class UserUpdateDTO {

    /**
     * 用户名
     */
    @NotBlank(message = "user.name.cant.be.null", groups = UpdateGroup.class)
    @Length(min = 1, max = 10, message = "user.name.out.of.length", groups = UpdateGroup.class)
    private String name;

    /**
     * 状态:0.未验证1.已验证2.已禁用
     */
    @NotNull(message = "user.status.cant.be.null", groups = UpdateGroup.class)
    private Integer status;

}
