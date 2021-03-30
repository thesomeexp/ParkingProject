package com.someexp.common.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

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
    private boolean hasNext;

    /**
     * 总记录数(为了性能这个管理员才有)
     */
    private int total;

    /**
     * 总页数
     */
    private int pages;

    public AdminPageResultDTO(PageParamQuery query, List<T> result, int total) {
        this.setPageNum(query.getPageNum());
        this.setPageSize(query.getPageSize());
        int sum = result.size();
        // 根据是否多返回一行数据来判断是否有下一页, 提高性能
        if (sum > this.getPageSize()) {
            result.remove(sum - 1);
            this.hasNext = true;
        } else {
            this.hasNext = false;
        }
        this.setList(result);
        this.total = total;
        this.pages = getPageSize() > 0 ? (total % getPageSize() == 0 ? total / getPageSize() : total / getPageSize() + 1) : 0;
    }

}
