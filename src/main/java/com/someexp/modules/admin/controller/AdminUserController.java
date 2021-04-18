package com.someexp.modules.admin.controller;

import com.someexp.common.domain.Result;
import com.someexp.common.validator.ValidatorUtils;
import com.someexp.common.validator.group.QueryGroup;
import com.someexp.common.validator.group.UpdateGroup;
import com.someexp.modules.admin.domain.dto.UserUpdateDTO;
import com.someexp.modules.admin.domain.query.UserQuery;
import com.someexp.modules.admin.service.AdminUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author someexp
 * @Date 2021/4/18
 */
@RestController
public class AdminUserController {

    @Resource
    private AdminUserService adminUserService;

    @GetMapping("/admin/user")
    public Result list(UserQuery userQuery) {
        ValidatorUtils.validateEntity(userQuery, QueryGroup.class);
        return Result.success(adminUserService.list(userQuery));
    }

    @GetMapping("/admin/user/{id}")
    public Result get(@PathVariable("id") Long id) {
        return Result.success(adminUserService.get(id));
    }

    @PutMapping("/admin/user/{id}")
    public Result update(@PathVariable("id") Long id, @RequestBody UserUpdateDTO userUpdateDTO) {
        ValidatorUtils.validateEntity(userUpdateDTO, UpdateGroup.class);
        return Result.success(adminUserService.update(id, userUpdateDTO));
    }

}
