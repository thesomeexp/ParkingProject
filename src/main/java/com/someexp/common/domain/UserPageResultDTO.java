package com.someexp.common.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 用户的分页结果
 *
 * @param <T>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPageResultDTO<T> extends PageResultDTO<T> {

    /**
     * 是否有下一页
     */
    private boolean hasNext;

    public UserPageResultDTO(PageParamQuery query, List<T> result) {
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
    }

}
