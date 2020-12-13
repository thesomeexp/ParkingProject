package com.someexp.modules.admin.controller;

import com.someexp.common.domain.Result;
import com.someexp.common.utils.MsgUtils;
import com.someexp.common.validator.ValidatorUtils;
import com.someexp.common.validator.group.QueryGroup;
import com.someexp.modules.admin.domain.query.ParkingQuery;
import com.someexp.modules.admin.service.AdminParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

//    @PutMapping("/admin/parking/{id}")
//    public ResponseEntity<?> put(ParkingUpdateDTO parkingUpdateDTO) {
//
//    }

}
