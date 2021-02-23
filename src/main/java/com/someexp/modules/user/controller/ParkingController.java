package com.someexp.modules.user.controller;

import com.someexp.common.domain.Result;
import com.someexp.common.exception.ParamsException;
import com.someexp.common.utils.MsgUtils;
import com.someexp.common.validator.ValidatorUtils;
import com.someexp.common.validator.group.AddGroup;
import com.someexp.common.validator.group.QueryGroup;
import com.someexp.modules.user.domain.dto.ParkingDTO;
import com.someexp.modules.user.domain.query.ParkingQuery;
import com.someexp.modules.user.service.ParkingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author someexp
 * @date 2020/12/3
 */
@RestController
public class ParkingController {

    private static final Logger log = LoggerFactory.getLogger(ParkingController.class);

    @Autowired
    private ParkingService parkingService;

    @GetMapping("/parking")
    public ResponseEntity<?> list(ParkingQuery parkingQuery) {
        ValidatorUtils.validateEntity(parkingQuery, QueryGroup.class);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Result.success(parkingService.list(parkingQuery), MsgUtils.get("success")));
    }

    @PostMapping("/parking")
    public ResponseEntity<?> add(ParkingDTO parkingDTO) {
        ValidatorUtils.validateEntity(parkingDTO, AddGroup.class);
        if (parkingDTO.getImage().isEmpty()) {
            throw new ParamsException(MsgUtils.get("parking.image.cant.be.null"));
        }
        // 检查文件后缀
        String filename = parkingDTO.getImage().getOriginalFilename();
        if (filename == null || (!filename.endsWith(".png") && !filename.endsWith(".jpg"))) {
            throw new ParamsException(MsgUtils.get("parking.image.not.a.image"));
        }

        String name = parkingService.add(parkingDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Result.success(name, MsgUtils.get("parking.successful.application")));
    }

    @GetMapping("/parking/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(Result.success(parkingService.get(id), MsgUtils.get("success")));
    }

}
