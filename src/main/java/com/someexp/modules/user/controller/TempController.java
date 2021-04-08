package com.someexp.modules.user.controller;

import com.someexp.common.domain.PageParamQuery;
import com.someexp.common.domain.Result;
import com.someexp.common.utils.MsgUtils;
import com.someexp.common.validator.ValidatorUtils;
import com.someexp.common.validator.group.AddGroup;
import com.someexp.common.validator.group.QueryGroup;
import com.someexp.modules.user.domain.dto.TempDTO;
import com.someexp.modules.user.domain.query.TempQuery;
import com.someexp.modules.user.service.TempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author someexp
 * @date 2020/12/31
 */
@RestController
public class TempController {

    @Autowired
    private TempService tempService;

    /**
     * 用户给停车场添加拥挤度状态信息
     *
     * @param tempDTO
     * @return
     */
    @PostMapping("/temp")
    public ResponseEntity<?> add(@RequestBody TempDTO tempDTO) throws NoSuchFieldException, IllegalAccessException {
        ValidatorUtils.validateEntity(tempDTO, AddGroup.class);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Result.success(tempService.add(tempDTO), MsgUtils.get("temp.successful.added")));
    }

    /**
     * 用户列出停车场近10分钟的状态信息, 最新5条Temp数据
     *
     * @param tempQuery
     * @return
     */
    @GetMapping("/temp")
    public ResponseEntity<?> list(TempQuery tempQuery) {
        ValidatorUtils.validateEntity(tempQuery, QueryGroup.class);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Result.success(tempService.list(tempQuery.getPid()), MsgUtils.get("success")));
    }

    /**
     * 用户获取10分钟内的拥挤度, 如果没有结果返回-1
     *
     * @param tempQuery
     * @return
     */
    @GetMapping("/temp/interval")
    public ResponseEntity<?> tempInterval(TempQuery tempQuery) {
        ValidatorUtils.validateEntity(tempQuery, QueryGroup.class);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Result.success(tempService.interval(tempQuery.getPid()), MsgUtils.get("success")));
    }

    @GetMapping("/temp/my")
    public ResponseEntity<?> listMyTemp(PageParamQuery pageParamQuery) {
        ValidatorUtils.validateEntity(pageParamQuery, QueryGroup.class);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Result.success(tempService.listMyTemp(pageParamQuery), MsgUtils.get("success")));
    }

}
