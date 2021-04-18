package com.someexp.modules.admin.controller;

import com.someexp.common.domain.Result;
import com.someexp.common.validator.ValidatorUtils;
import com.someexp.common.validator.group.QueryGroup;
import com.someexp.common.validator.group.UpdateGroup;
import com.someexp.modules.admin.domain.dto.FeedbackDTO;
import com.someexp.modules.admin.domain.query.FeedbackQuery;
import com.someexp.modules.admin.service.AdminFeedbackService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author someexp
 * @Date 2021/4/1
 */
@RestController
public class AdminFeedbackController {

    @Resource
    private AdminFeedbackService adminFeedbackService;

    @GetMapping("/admin/feedback")
    public Result list(FeedbackQuery feedbackQuery) {
        ValidatorUtils.validateEntity(feedbackQuery, QueryGroup.class);
        return Result.success(adminFeedbackService.list(feedbackQuery));
    }

    @PatchMapping("/admin/feedback/{id}")
    public Result update(@PathVariable("id") Long id, @RequestBody FeedbackDTO feedbackDTO) {
        ValidatorUtils.validateEntity(feedbackDTO, UpdateGroup.class);
        return Result.success(adminFeedbackService.update(id, feedbackDTO));
    }

}
