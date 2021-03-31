package com.someexp.modules.admin.service;

import com.someexp.common.domain.PageResultDTO;
import com.someexp.modules.admin.domain.query.ReviewQuery;
import com.someexp.modules.admin.domain.entity.Review;

/**
 * @Author someexp
 * @Date 2021/3/31
 */
public interface AdminReviewService {
    PageResultDTO<?> list(ReviewQuery reviewQuery);

    Review get(Long id);
}
