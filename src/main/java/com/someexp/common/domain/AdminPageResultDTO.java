package com.someexp.common.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 管理员的分页结果
 *
 * @param <T>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AdminPageResultDTO<T> extends PageResultDTO<T> {

    /**
     * 是否有下一页
     */
    private int hasNext;

    /**
     * 总记录数(为了性能这个管理员才有)
     */
    private long total;

    /**
     * 总页数
     */
    private int pages;

}
