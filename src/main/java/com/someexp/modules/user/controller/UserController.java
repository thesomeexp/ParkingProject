package com.someexp.modules.user.controller;

import com.someexp.common.domain.Result;
import com.someexp.common.utils.MsgUtils;
import com.someexp.common.validator.ValidatorUtils;
import com.someexp.common.validator.group.LoginGroup;
import com.someexp.common.validator.group.RegisterGroup;
import com.someexp.modules.user.domain.dto.UserDTO;
import com.someexp.modules.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author someexp
 * @date 2020/9/23
 */
@RestController
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
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

        String name = userService.register(userDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Result.success(name, MsgUtils.get("user.register.success")));
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

        String jwt = userService.login(userDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Result.success(jwt, MsgUtils.get("user.login.success")));
    }

}
