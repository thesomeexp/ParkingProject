package com.someexp.modules.user.controller;

import com.someexp.common.domain.Result;
import com.someexp.common.utils.MsgUtils;
import com.someexp.common.validator.ValidatorUtils;
import com.someexp.common.validator.group.LoginGroup;
import com.someexp.common.validator.group.RegisterGroup;
import com.someexp.modules.user.domain.dto.UserDTO;
import com.someexp.modules.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author someexp
 * @date 2020/9/23
 */
@RestController
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 注册
     *
     * @param userDTO
     * @return
     */
    @PostMapping("/user/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        ValidatorUtils.validateEntity(userDTO, RegisterGroup.class);
        // 本地测试时注册仅为 +86
        if (userDTO.getAreaCode() != 86) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Result.fail(MsgUtils.get("user.register.only.86")));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(Result.success(userService.register(userDTO), MsgUtils.get("user.register.success")));
    }

    /**
     * 登录
     *
     * @param userDTO
     * @return
     */
    @PostMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        ValidatorUtils.validateEntity(userDTO, LoginGroup.class);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Result.success(userService.login(userDTO), MsgUtils.get("user.login.success")));
    }

    @GetMapping("/user/profile")
    public ResponseEntity<?> getProfile() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(Result.success(userService.getProfile(), MsgUtils.get("success")));
    }

}
