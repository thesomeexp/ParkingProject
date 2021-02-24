package com.someexp.modules.user.service.impl;

import com.someexp.common.domain.PageResultDTO;
import com.someexp.common.domain.UserPageResultDTO;
import com.someexp.common.exception.BusinessException;
import com.someexp.common.utils.BeanUtils;
import com.someexp.common.utils.MsgUtils;
import com.someexp.common.utils.ShiroUtils;
import com.someexp.modules.user.domain.dto.ReviewDTO;
import com.someexp.modules.user.domain.entity.Review;
import com.someexp.modules.user.domain.query.ReviewQuery;
import com.someexp.modules.user.mapper.ReviewMapper;
import com.someexp.modules.user.service.ParkingService;
import com.someexp.modules.user.service.ReviewService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author someexp
 * @date 2021/1/7
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    @Resource
    private ReviewMapper reviewMapper;

    @Resource
    private ParkingService parkingService;

    @Override
    public Integer add(ReviewDTO reviewDTO) {
        if (!parkingService.checkParkingExists(reviewDTO.getPid())) {
            throw new BusinessException(MsgUtils.get("parking.not.exist"));
        }
        Long userId = ShiroUtils.getUserId();
        if (reviewMapper.checkReviewExists(reviewDTO.getPid(), userId)) {
            throw new BusinessException((MsgUtils.get("review.already.exist")));
        }
        Review review = new Review();
        BeanUtils.copyProperties(reviewDTO, review);
        review.setUid(userId);

        reviewMapper.save(review);
        return review.getScore();
    }

    @Override
    public PageResultDTO<?> list(ReviewQuery reviewQuery) {
        if (!parkingService.checkParkingExists(reviewQuery.getPid())) {
            throw new BusinessException(MsgUtils.get("parking.not.exist"));
        }

        return new UserPageResultDTO<>(reviewQuery,
                reviewMapper.pageByPid(reviewQuery.getPid(), reviewQuery.getOffset(), reviewQuery.getRowCount())
        );
    }

}
