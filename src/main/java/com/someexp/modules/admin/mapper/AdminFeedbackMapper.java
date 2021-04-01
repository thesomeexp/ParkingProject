package com.someexp.modules.admin.mapper;

import com.someexp.modules.admin.domain.entity.Feedback;
import com.someexp.modules.admin.domain.query.FeedbackQuery;

import java.util.List;

/**
 * @Author someexp
 * @Date 2021/4/1
 */
public interface AdminFeedbackMapper {
    List<Feedback> listByPage(FeedbackQuery feedbackQuery);

    int countByPage(FeedbackQuery feedbackQuery);

    Feedback get(Long id);

    void update(Feedback feedback);
}
