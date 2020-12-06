package com.someexp.common.domain;

import lombok.Data;

import java.util.List;

/**
 * @author someexp
 * @date 2020/9/22
 */
@Data
public class PageResultDTO<T> {

    /**
     * 当前页
     */
    private Long pageNum;

    /**
     * 每页的数量
     */
    private Long pageSize;

    /**
     * 总记录数
     */
    private Long total;

    /**
     * 总页数
     */
    private Long pages;

    /**
     * 结果集
     */
    private List<T> list;

}
