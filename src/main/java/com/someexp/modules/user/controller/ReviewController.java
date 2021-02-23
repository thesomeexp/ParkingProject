package com.someexp.modules.user.controller;

import com.someexp.common.domain.Result;
import com.someexp.common.utils.MsgUtils;
import com.someexp.common.validator.ValidatorUtils;
import com.someexp.common.validator.group.AddGroup;
import com.someexp.common.validator.group.QueryGroup;
import com.someexp.modules.user.domain.dto.ReviewDTO;
import com.someexp.modules.user.domain.query.ReviewQuery;
import com.someexp.modules.user.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author someexp
 * @date 2021/1/7
 */
@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/review")
    public ResponseEntity<?> add(@RequestBody ReviewDTO reviewDTO) {
        ValidatorUtils.validateEntity(reviewDTO, AddGroup.class);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Result.success(reviewService.add(reviewDTO), MsgUtils.get("success")));
    }

    /**
     * 列出一个停车场的评论
     *
     * @param reviewQuery
     * @return
     */
    @GetMapping("/review")
    public ResponseEntity<?> list(ReviewQuery reviewQuery) {
        ValidatorUtils.validateEntity(reviewQuery, QueryGroup.class);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Result.success(reviewService.list(reviewQuery), MsgUtils.get("success")));
    }

}
