package com.someexp.modules.user.controller;

import com.someexp.common.domain.PageParamQuery;
import com.someexp.common.domain.Result;
import com.someexp.common.exception.ParamsException;
import com.someexp.common.utils.MsgUtils;
import com.someexp.common.validator.ValidatorUtils;
import com.someexp.common.validator.group.AddGroup;
import com.someexp.common.validator.group.QueryGroup;
import com.someexp.modules.user.domain.dto.ParkingDTO;
import com.someexp.modules.user.domain.query.ParkingQuery;
import com.someexp.modules.user.service.ParkingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author someexp
 * @date 2020/12/3
 */
@RestController
public class ParkingController {

    @Resource
    private ParkingService parkingService;

    @PostMapping("/parking")
    public Result add(ParkingDTO parkingDTO) {
        ValidatorUtils.validateEntity(parkingDTO, AddGroup.class);
        if (parkingDTO.getImage() == null || parkingDTO.getImage().isEmpty()) {
            throw new ParamsException(MsgUtils.get("parking.image.cant.be.null"));
        }
        // 检查文件后缀
        String filename = parkingDTO.getImage().getOriginalFilename();
        if (filename == null || (!filename.endsWith(".png") && !filename.endsWith(".jpg"))) {
            throw new ParamsException(MsgUtils.get("parking.image.not.a.image"));
        }
        return Result.success(parkingService.add(parkingDTO), MsgUtils.get("parking.successful.application"));
    }

    @GetMapping("/parking")
    public Result list(ParkingQuery parkingQuery) {
        ValidatorUtils.validateEntity(parkingQuery, QueryGroup.class);
        return Result.success(parkingService.list(parkingQuery));
    }

    @GetMapping("/parking/{id}")
    public Result get(@PathVariable("id") Long id) {
        return Result.success(parkingService.get(id));
    }

    @GetMapping("/parking/my")
    public Result listMyParking(PageParamQuery pageParamQuery) {
        ValidatorUtils.validateEntity(pageParamQuery, QueryGroup.class);
        return Result.success(parkingService.listMyParking(pageParamQuery));
    }

    @PostMapping("/parking/reduceFree/{id}")
    public Result reduceFree(@PathVariable("id") Long id) {
        return Result.success(parkingService.reduceFree(id));
    }

}
