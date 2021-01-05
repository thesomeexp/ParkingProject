package com.someexp.modules.user.controller;

import com.someexp.common.domain.Result;
import com.someexp.common.utils.MsgUtils;
import com.someexp.common.validator.ValidatorUtils;
import com.someexp.common.validator.group.AddGroup;
import com.someexp.modules.user.domain.dto.TempUserDTO;
import com.someexp.modules.user.service.TempUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author someexp
 * @date 2021/1/5
 */
@RestController
public class TempUserController {

    @Autowired
    private TempUserService tempUserService;

    @PostMapping("/tempUser")
    public ResponseEntity<?> add(@RequestBody TempUserDTO tempUserDTO) {
        ValidatorUtils.validateEntity(tempUserDTO, AddGroup.class);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Result.success(tempUserService.add(tempUserDTO), MsgUtils.get("tempUser.add.success")));
    }

}