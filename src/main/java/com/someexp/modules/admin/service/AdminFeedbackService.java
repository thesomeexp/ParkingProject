package com.someexp.modules.admin.service;

import com.someexp.common.domain.PageResultDTO;
import com.someexp.modules.admin.domain.dto.FeedbackDTO;
import com.someexp.modules.admin.domain.query.FeedbackQuery;

/**
 * @Author someexp
 * @Date 2021/4/1
 */
public interface AdminFeedbackService {

    PageResultDTO<?> list(FeedbackQuery feedbackQuery);


    Long update(Long id, FeedbackDTO feedbackDTO);
}
