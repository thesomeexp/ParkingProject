package com.someexp.modules.admin.controller;

import com.someexp.common.domain.Result;
import com.someexp.common.utils.MsgUtils;
import com.someexp.common.validator.ValidatorUtils;
import com.someexp.common.validator.group.QueryGroup;
import com.someexp.modules.admin.domain.query.ReviewQuery;
import com.someexp.modules.admin.service.AdminReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author someexp
 * @Date 2021/3/31
 */
@RestController
public class AdminReviewController {

    @Autowired
    private AdminReviewService adminReviewService;

    @GetMapping("/admin/review")
    public ResponseEntity<?> list(ReviewQuery reviewQuery) {
        ValidatorUtils.validateEntity(reviewQuery, QueryGroup.class);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Result.success(adminReviewService.list(reviewQuery), MsgUtils.get("success")));
    }

    @GetMapping("/admin/review/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(Result.success(adminReviewService.get(id), MsgUtils.get("success")));
    }

}
