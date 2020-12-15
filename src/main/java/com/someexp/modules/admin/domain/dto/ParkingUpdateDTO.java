package com.someexp.modules.admin.domain.dto;

import com.someexp.common.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author someexp
 * @date 2020/12/13
 */
@Data
public class ParkingUpdateDTO {

    /**
     * 停车场名称
     */
    @NotBlank(message = "parking.name.cant.be.null", groups = UpdateGroup.class)
    private String name;

    /**
     * 停车场描述
     */
    @NotBlank(message = "parking.content.cant.be.null", groups = UpdateGroup.class)
    private String content;

    /**
     * 坐标
     */
    @NotBlank(message = "parking.location.cant.be.null", groups = UpdateGroup.class)
    private String location;

    /**
     * 状态:0.未验证1.已验证2.已禁用
     */
    @NotNull(message = "parking.status.cant.be.null", groups = UpdateGroup.class)
    private Integer status;

}
