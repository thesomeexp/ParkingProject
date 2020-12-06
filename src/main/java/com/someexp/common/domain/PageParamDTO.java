package com.someexp.common.domain;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * @author someexp
 * @date 2020/9/22
 */
@Data
public class PageParamDTO {

    @NotNull(message = "分页参数不能为空")
    protected Integer pageNum;

    @NotNull(message = "每页数量不能为空")
    @Max(value = 500, message = "每页最大为500")
    protected Integer pageSize;

}
