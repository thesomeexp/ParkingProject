package com.someexp.modules.user.service;

import com.someexp.common.domain.PageParamQuery;
import com.someexp.common.domain.PageResultDTO;
import com.someexp.modules.user.domain.dto.FeedbackDTO;

/**
 * @Author someexp
 * @Date 2021/3/27
 */
public interface FeedbackService {

    Long add(FeedbackDTO feedbackDTO);

    PageResultDTO<?> listMyFeedback(PageParamQuery pageParamQuery);
    
}
