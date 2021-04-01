package com.someexp.modules.admin.domain.query;

import com.someexp.common.domain.PageParamQuery;
import com.someexp.common.validator.group.QueryGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @Author someexp
 * @Date 2021/4/1
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FeedbackQuery extends PageParamQuery {

    @NotNull(message = "feedback.status.cant.be.null", groups = QueryGroup.class)
    private Integer status;

}
