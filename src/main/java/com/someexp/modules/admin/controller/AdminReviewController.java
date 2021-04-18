package com.someexp.modules.admin.controller;

import com.someexp.common.domain.Result;
import com.someexp.common.validator.ValidatorUtils;
import com.someexp.common.validator.group.QueryGroup;
import com.someexp.modules.admin.domain.query.ReviewQuery;
import com.someexp.modules.admin.service.AdminReviewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author someexp
 * @Date 2021/3/31
 */
@RestController
public class AdminReviewController {

    @Resource
    private AdminReviewService adminReviewService;

    @GetMapping("/admin/review")
    public Result list(ReviewQuery reviewQuery) {
        ValidatorUtils.validateEntity(reviewQuery, QueryGroup.class);
        return Result.success(adminReviewService.list(reviewQuery));
    }

    @GetMapping("/admin/review/{id}")
    public Result get(@PathVariable("id") Long id) {
        return Result.success(adminReviewService.get(id));
    }

}
