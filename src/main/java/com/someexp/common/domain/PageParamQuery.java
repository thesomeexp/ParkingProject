package com.someexp.common.domain;

import com.someexp.common.validator.group.QueryGroup;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 用户的分页结果, 多取一行判断是否有下一页
 *
 * @author someexp
 * @date 2020/9/22
 */
@Data
public class PageParamQuery {

    @NotNull(message = "query.page.num.cant.be.null", groups = QueryGroup.class)
    @Min(value = 1, message = "query.page.num.out.of.min", groups = QueryGroup.class)
    protected Integer pageNum;

    @NotNull(message = "query.page.size.cant.be.null", groups = QueryGroup.class)
    @Max(value = 100, message = "query.page.size.out.of.max", groups = QueryGroup.class)
    @Min(value = 1, message = "query.page.size.out.of.min", groups = QueryGroup.class)
    protected Integer pageSize;

    public Integer getOffset() {
        return (pageNum - 1) * pageSize;
    }

    /**
     * 多拿一行数据用于判断是否拥有下一页
     *
     * @return
     */
    public Integer getRowCount() {
        return pageSize + 1;
    }

}
