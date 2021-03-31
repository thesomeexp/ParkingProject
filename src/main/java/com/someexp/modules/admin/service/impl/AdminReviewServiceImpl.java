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
        // 手动选择索引, 避免filesort
        if (reviewQuery.getUid() != null && reviewQuery.getPid() != null) {
            reviewQuery.setIndexName("review_rew_uid_rew_pid_uindex");
        } else if (reviewQuery.getUid() != null) {
            reviewQuery.setIndexName("review_rew_uid_index");
        } else if (reviewQuery.getPid() != null) {
            reviewQuery.setIndexName("review_rew_pid_index");
        } else {
            reviewQuery.setIndexName("PRIMARY");
        }
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
