package com.someexp.modules.user.domain.dto;

import com.someexp.common.validator.group.AddGroup;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author someexp
 * @date 2021/1/7
 */
@Data
public class ReviewDTO {

    @NotNull(message = "review.pid.cant.be.null", groups = AddGroup.class)
    private Long pid;

    @NotNull(message = "review.score.cant.be.null", groups = AddGroup.class)
    @Min(value = 0, message = "review.score.out.of.min", groups = AddGroup.class)
    @Max(value = 5, message = "review.score.out.of.max", groups = AddGroup.class)
    private Integer score;

    @NotBlank(message = "review.content.cant.be.null", groups = AddGroup.class)
    @Length(min = 1, max = 100, message = "review.content.out.of.length", groups = AddGroup.class)
    private String content;

}
