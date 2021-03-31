package com.someexp.modules.admin.domain.query;

import com.someexp.common.domain.PageParamQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author someexp
 * @Date 2021/3/31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ReviewQuery extends PageParamQuery {

    private String indexName;

    private Long uid;

    private Long pid;

}
