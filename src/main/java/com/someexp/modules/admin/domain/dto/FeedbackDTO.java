package com.someexp.modules.admin.domain.dto;

import com.someexp.common.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author someexp
 * @Date 2021/4/1
 */
@Data
public class FeedbackDTO {

    private Long id;

    @NotNull(message = "feedback.status.cant.be.null", groups = UpdateGroup.class)
    private Integer status;

}
