package com.someexp.modules.admin.controller;

import com.someexp.common.domain.Result;
import com.someexp.common.validator.ValidatorUtils;
import com.someexp.common.validator.group.QueryGroup;
import com.someexp.common.validator.group.UpdateGroup;
import com.someexp.modules.admin.domain.dto.ParkingUpdateDTO;
import com.someexp.modules.admin.domain.query.ParkingQuery;
import com.someexp.modules.admin.service.AdminParkingService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author someexp
 * @date 2020/12/7
 */
@RestController
public class AdminParkingController {

    @Resource
    private AdminParkingService adminParkingService;

    @GetMapping("/admin/parking")
    public Result list(ParkingQuery parkingQuery) {
        ValidatorUtils.validateEntity(parkingQuery, QueryGroup.class);
        return Result.success(adminParkingService.list(parkingQuery));
    }

    @GetMapping("/admin/parking/{id}")
    public Result get(@PathVariable("id") Long id) {
        return Result.success(adminParkingService.get(id));
    }

    @PutMapping("/admin/parking/{id}")
    public Result update(@PathVariable("id") Long id, @RequestBody ParkingUpdateDTO parkingUpdateDTO) {
        ValidatorUtils.validateEntity(parkingUpdateDTO, UpdateGroup.class);
        return Result.success(adminParkingService.update(id, parkingUpdateDTO));
    }

}
