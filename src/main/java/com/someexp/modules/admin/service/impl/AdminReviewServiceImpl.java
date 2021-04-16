package com.someexp.modules.admin.service.impl;

import com.someexp.common.domain.AdminPageResultDTO;
import com.someexp.common.domain.PageResultDTO;
import com.someexp.common.exception.ParamsException;
import com.someexp.common.utils.MsgUtils;
import com.someexp.modules.admin.domain.entity.Review;
import com.someexp.modules.admin.domain.query.ReviewQuery;
import com.someexp.modules.admin.mapper.AdminReviewMapper;
import com.someexp.modules.admin.service.AdminReviewService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author someexp
 * @Date 2021/3/31
 */
@Service
public class AdminReviewServiceImpl implements AdminReviewService {

    @Resource
    private AdminReviewMapper adminReviewMapper;

    @Override
    public PageResultDTO<?> list(ReviewQuery reviewQuery) {
        return new AdminPageResultDTO<>(reviewQuery,
                adminReviewMapper.listByPage(reviewQuery),
                adminReviewMapper.countByPage(reviewQuery));
    }

    @Override
    public Review get(Long id) {
        Review review = adminReviewMapper.get(id);
        if (review == null) {
            throw new ParamsException(MsgUtils.get("review.not.exist"));
        }
        return review;
    }
}
