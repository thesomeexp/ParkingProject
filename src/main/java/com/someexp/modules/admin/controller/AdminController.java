package com.someexp.modules.admin.controller;

import com.someexp.common.domain.Result;
import com.someexp.common.utils.MsgUtils;
import com.someexp.common.validator.ValidatorUtils;
import com.someexp.common.validator.group.LoginGroup;
import com.someexp.modules.admin.domain.dto.AdminDTO;
import com.someexp.modules.admin.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author someexp
 * @date 2020/10/28
 */
@RestController
public class AdminController {

    @Resource
    private AdminService adminService;

    @PostMapping("/admin/login")
    public ResponseEntity<?> login(@RequestBody AdminDTO adminDTO) {
        ValidatorUtils.validateEntity(adminDTO, LoginGroup.class);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Result.success(adminService.login(adminDTO), MsgUtils.get("admin.login.success")));
    }

    @GetMapping("/admin/home")
    public ResponseEntity<?> home() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(Result.success(adminService.home(), MsgUtils.get("success")));
    }

}
