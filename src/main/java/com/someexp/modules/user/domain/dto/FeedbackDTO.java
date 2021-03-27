package com.someexp.modules.user.domain.dto;

import com.someexp.common.validator.group.AddGroup;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author someexp
 * @Date 2021/3/27
 */
@Data
public class FeedbackDTO {

    @NotNull(message = "feedback.pid.cant.be.null", groups = AddGroup.class)
    private Long pid;

    @NotBlank(message = "feedback.content.cant.be.null", groups = AddGroup.class)
    @Length(min = 1, max = 255, message = "feedback.content.out.of.length", groups = AddGroup.class)
    private String content;

    @NotBlank(message = "feedback.contact.cant.be.null", groups = AddGroup.class)
    @Length(min = 1, max = 30, message = "feedback.contact.out.of.length", groups = AddGroup.class)
    private String contact;

}
