package com.someexp.modules.admin.controller;

import com.someexp.common.domain.Result;
import com.someexp.common.utils.MsgUtils;
import com.someexp.common.validator.ValidatorUtils;
import com.someexp.common.validator.group.QueryGroup;
import com.someexp.common.validator.group.UpdateGroup;
import com.someexp.modules.admin.domain.dto.ParkingUpdateDTO;
import com.someexp.modules.admin.domain.query.ParkingQuery;
import com.someexp.modules.admin.service.AdminParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author someexp
 * @date 2020/12/7
 */
@RestController
public class AdminParkingController {

    @Autowired
    private AdminParkingService adminParkingService;

    @GetMapping("/admin/parking")
    public ResponseEntity<?> list(ParkingQuery parkingQuery) {
        ValidatorUtils.validateEntity(parkingQuery, QueryGroup.class);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Result.success(adminParkingService.list(parkingQuery), MsgUtils.get("success")));
    }

    @GetMapping("/admin/parking/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(Result.success(adminParkingService.get(id), MsgUtils.get("success")));
    }

    @PutMapping("/admin/parking/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ParkingUpdateDTO parkingUpdateDTO) {
        ValidatorUtils.validateEntity(parkingUpdateDTO, UpdateGroup.class);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Result.success(adminParkingService.update(id, parkingUpdateDTO), MsgUtils.get("success")));
    }

}
