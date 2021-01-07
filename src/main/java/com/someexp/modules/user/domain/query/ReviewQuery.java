package com.someexp.modules.user.domain.query;

import com.someexp.common.domain.PageParamQuery;
import com.someexp.common.validator.group.QueryGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author someexp
 * @date 2021/1/7
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ReviewQuery extends PageParamQuery {

    @NotNull(message = "review.pid.cant.be.null", groups = QueryGroup.class)
    private Long pid;

}
