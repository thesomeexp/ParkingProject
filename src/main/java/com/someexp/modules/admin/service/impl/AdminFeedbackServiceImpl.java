package com.someexp.modules.admin.service.impl;

import com.someexp.common.domain.AdminPageResultDTO;
import com.someexp.common.domain.PageResultDTO;
import com.someexp.common.exception.ParamsException;
import com.someexp.common.utils.MsgUtils;
import com.someexp.modules.admin.domain.dto.FeedbackDTO;
import com.someexp.modules.admin.domain.entity.Feedback;
import com.someexp.modules.admin.domain.query.FeedbackQuery;
import com.someexp.modules.admin.mapper.AdminFeedbackMapper;
import com.someexp.modules.admin.service.AdminFeedbackService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author someexp
 * @Date 2021/4/1
 */
@Service
public class AdminFeedbackServiceImpl implements AdminFeedbackService {

    @Resource
    private AdminFeedbackMapper adminFeedbackMapper;

    @Override
    public PageResultDTO<?> list(FeedbackQuery feedbackQuery) {
        return new AdminPageResultDTO<>(feedbackQuery,
                adminFeedbackMapper.listByPage(feedbackQuery),
                adminFeedbackMapper.countByPage(feedbackQuery));
    }

    @Override
    public Long update(Long id, FeedbackDTO feedbackDTO) {
        Feedback feedback = adminFeedbackMapper.get(id);
        if (feedback == null) {
            throw new ParamsException(MsgUtils.get("feedback.not.exist"));
        }

        feedback.setStatus(feedbackDTO.getStatus());

        adminFeedbackMapper.update(feedback);
        return id;
    }
}
