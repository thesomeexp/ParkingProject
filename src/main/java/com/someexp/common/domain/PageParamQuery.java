package com.someexp.common.domain;

import com.someexp.common.validator.group.QueryGroup;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * @author someexp
 * @date 2020/9/22
 */
@Data
public class PageParamQuery {

    @NotNull(message = "query.page.num.cant.be.null", groups = QueryGroup.class)
    protected Integer pageNum;

    @NotNull(message = "query.page.size.cant.be.null", groups = QueryGroup.class)
    @Max(value = 100, message = "query.page.size.out.of.max", groups = QueryGroup.class)
    protected Integer pageSize;

}
