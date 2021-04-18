package com.someexp.modules.admin.controller;

import com.someexp.common.domain.PageParamQuery;
import com.someexp.common.domain.Result;
import com.someexp.common.validator.ValidatorUtils;
import com.someexp.common.validator.group.AddGroup;
import com.someexp.common.validator.group.Ban;
import com.someexp.common.validator.group.QueryGroup;
import com.someexp.modules.admin.domain.dto.AdminDTO;
import com.someexp.modules.admin.service.AdminAccountService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 管理员管理管理员相关
 *
 * @Author someexp
 * @Date 2021/4/1
 */
@RestController
public class AdminAccountController {

    @Resource
    private AdminAccountService adminAccountService;

    /**
     * 添加管理员
     *
     * @param adminDTO
     * @return
     */
    @PostMapping("/admin/account")
    public Result add(@RequestBody AdminDTO adminDTO) {
        ValidatorUtils.validateEntity(adminDTO, AddGroup.class);
        return Result.success(adminAccountService.add(adminDTO));
    }

    /**
     * 列出所有管理员
     *
     * @param pageParamQuery
     * @return
     */
    @GetMapping("/admin/account")
    public Result list(PageParamQuery pageParamQuery) {
        ValidatorUtils.validateEntity(pageParamQuery, QueryGroup.class);
        return Result.success(adminAccountService.list(pageParamQuery));
    }

    /**
     * 管理员创造链查询
     *
     * @param id
     * @return
     */
    @GetMapping("/admin/account/parent/{id}")
    public Result getParent(@PathVariable("id") Long id) {
        return Result.success(adminAccountService.getParent(id));
    }

    /**
     * 关联封禁, 封禁当前账号以及其创建的账号
     *
     * @param adminDTO
     * @return
     */
    @PostMapping("/admin/account/ban")
    public Result ban(@RequestBody AdminDTO adminDTO) {
        ValidatorUtils.validateEntity(adminDTO, Ban.class);
        return Result.success(adminAccountService.ban(adminDTO));
    }

}
