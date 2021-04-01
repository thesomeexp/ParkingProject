package com.someexp.modules.admin.controller;

import com.someexp.common.domain.Result;
import com.someexp.common.utils.MsgUtils;
import com.someexp.common.validator.ValidatorUtils;
import com.someexp.common.validator.group.QueryGroup;
import com.someexp.common.validator.group.UpdateGroup;
import com.someexp.modules.admin.domain.dto.FeedbackDTO;
import com.someexp.modules.admin.domain.query.FeedbackQuery;
import com.someexp.modules.admin.service.AdminFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author someexp
 * @Date 2021/4/1
 */
@RestController
public class AdminFeedbackController {

    @Autowired
    private AdminFeedbackService adminFeedbackService;

    @GetMapping("/admin/feedback")
    public ResponseEntity<?> list(FeedbackQuery feedbackQuery) {
        ValidatorUtils.validateEntity(feedbackQuery, QueryGroup.class);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Result.success(adminFeedbackService.list(feedbackQuery), MsgUtils.get("success")));
    }

    @PatchMapping("/admin/feedback/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody FeedbackDTO feedbackDTO) {
        ValidatorUtils.validateEntity(feedbackDTO, UpdateGroup.class);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Result.success(adminFeedbackService.update(id, feedbackDTO), MsgUtils.get("success")));
    }
}
