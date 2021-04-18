package com.someexp.modules.user.controller;

import com.someexp.common.domain.PageParamQuery;
import com.someexp.common.domain.Result;
import com.someexp.common.validator.ValidatorUtils;
import com.someexp.common.validator.group.AddGroup;
import com.someexp.common.validator.group.QueryGroup;
import com.someexp.modules.user.domain.dto.ReviewDTO;
import com.someexp.modules.user.domain.query.ReviewQuery;
import com.someexp.modules.user.service.ReviewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author someexp
 * @date 2021/1/7
 */
@RestController
public class ReviewController {

    @Resource
    private ReviewService reviewService;

    @PostMapping("/review")
    public Result add(@RequestBody ReviewDTO reviewDTO) {
        ValidatorUtils.validateEntity(reviewDTO, AddGroup.class);
        return Result.success(reviewService.add(reviewDTO));
    }

    @GetMapping("/review")
    public Result list(ReviewQuery reviewQuery) {
        ValidatorUtils.validateEntity(reviewQuery, QueryGroup.class);
        return Result.success(reviewService.list(reviewQuery));
    }

    @GetMapping("/review/my")
    public Result listMyReview(PageParamQuery pageParamQuery) {
        ValidatorUtils.validateEntity(pageParamQuery, QueryGroup.class);
        return Result.success(reviewService.listMyReview(pageParamQuery));
    }

}
