package com.someexp.modules.user.service;

import com.someexp.common.domain.PageResultDTO;
import com.someexp.modules.user.domain.dto.ReviewDTO;
import com.someexp.modules.user.domain.query.ReviewQuery;

/**
 * @author someexp
 * @date 2021/1/7
 */
public interface ReviewService {
    /**
     * @param reviewDTO
     * @return 提交的评分
     */
    Integer add(ReviewDTO reviewDTO);

    PageResultDTO<?> list(ReviewQuery reviewQuery);
}
