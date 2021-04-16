package com.someexp.modules.admin.mapper;

import com.someexp.modules.admin.domain.query.ReviewQuery;
import com.someexp.modules.admin.domain.entity.Review;

import java.util.List;

/**
 * @Author someexp
 * @Date 2021/3/31
 */
public interface AdminReviewMapper {

    List<Review> listByPage(ReviewQuery reviewQuery);

    int countByPage(ReviewQuery reviewQuery);

    Review get(Long id);

}
