package com.someexp.modules.user.service.impl;

import com.someexp.common.domain.PageParamQuery;
import com.someexp.common.domain.PageResultDTO;
import com.someexp.common.domain.UserPageResultDTO;
import com.someexp.common.exception.BusinessException;
import com.someexp.common.utils.BeanUtils;
import com.someexp.common.utils.MsgUtils;
import com.someexp.common.utils.ShiroUtils;
import com.someexp.modules.user.domain.dto.FeedbackDTO;
import com.someexp.modules.user.domain.entity.Feedback;
import com.someexp.modules.user.mapper.FeedbackMapper;
import com.someexp.modules.user.service.FeedbackService;
import com.someexp.modules.user.service.ParkingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author someexp
 * @Date 2021/3/27
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Resource
    private FeedbackMapper feedbackMapper;

    @Resource
    private ParkingService parkingService;

    @Override
    public Long add(FeedbackDTO feedbackDTO) {
        if (!parkingService.checkParkingExists(feedbackDTO.getPid())) {
            throw new BusinessException(MsgUtils.get("parking.not.exist"));
        }
        Feedback feedback = new Feedback();
        BeanUtils.copyProperties(feedbackDTO, feedback);
        feedback.setUid(ShiroUtils.getUserId());
        feedbackMapper.save(feedback);
        return feedback.getId();
    }

    @Override
    public PageResultDTO<?> listMyFeedback(PageParamQuery pageParamQuery) {
        return new UserPageResultDTO<>(pageParamQuery,
                feedbackMapper.pageByUid(ShiroUtils.getUserId(), pageParamQuery.getOffset(), pageParamQuery.getRowCount()));
    }

}
