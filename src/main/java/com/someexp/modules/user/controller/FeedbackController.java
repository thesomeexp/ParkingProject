package com.someexp.modules.user.controller;

import com.someexp.common.domain.PageParamQuery;
import com.someexp.common.domain.Result;
import com.someexp.common.utils.MsgUtils;
import com.someexp.common.validator.ValidatorUtils;
import com.someexp.common.validator.group.AddGroup;
import com.someexp.common.validator.group.QueryGroup;
import com.someexp.modules.user.domain.dto.FeedbackDTO;
import com.someexp.modules.user.service.FeedbackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class FeedbackController {

    @Resource
    private FeedbackService feedbackService;

    @PostMapping("/feedback")
    public ResponseEntity<?> add(@RequestBody FeedbackDTO feedbackDTO) {
        ValidatorUtils.validateEntity(feedbackDTO, AddGroup.class);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Result.success(feedbackService.add(feedbackDTO), MsgUtils.get("feedback.success")));
    }

    @GetMapping("/feedback/my")
    public ResponseEntity<?> listMyReview(PageParamQuery pageParamQuery) {
        ValidatorUtils.validateEntity(pageParamQuery, QueryGroup.class);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Result.success(feedbackService.listMyFeedback(pageParamQuery), MsgUtils.get("success")));
    }

}
