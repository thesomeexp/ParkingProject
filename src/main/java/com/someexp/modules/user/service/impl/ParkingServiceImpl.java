package com.someexp.modules.user.service.impl;

import com.someexp.common.exception.BusinessException;
import com.someexp.common.utils.LocationUtils;
import com.someexp.common.utils.MsgUtils;
import com.someexp.common.utils.ShiroUtils;
import com.someexp.common.variable.CommonVariable;
import com.someexp.modules.user.domain.dto.ParkingDTO;
import com.someexp.modules.user.domain.entity.Parking;
import com.someexp.modules.user.mapper.ParkingMapper;
import com.someexp.modules.user.service.ParkingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author someexp
 * @date 2020/12/3
 */
@Service
public class ParkingServiceImpl implements ParkingService {

    private static final Logger log = LoggerFactory.getLogger(ParkingServiceImpl.class);

    @Resource
    private ParkingMapper parkingMapper;

    /**
     * 检查是否存在
     * 先插入数据库, 再上传图片
     *
     * @param parkingDTO
     * @return
     */
    @Transactional
    @Override
    public String add(ParkingDTO parkingDTO) {
        Parking parking = new Parking();
        parking.setName(parkingDTO.getName());
        parking.setContent(parkingDTO.getContent());
        parking.setUid(ShiroUtils.getUserId());

        double[] xyArray = LocationUtils.parseLocation(parkingDTO.getLocation());
        parking.setLongitude(xyArray[0]);
        parking.setLatitude(xyArray[1]);
        parking.setStateUpdateDate(LocalDateTime.now());

        if (isLocationExist(parking.getLongitude(), parking.getLatitude())) {
            throw new BusinessException(MsgUtils.get("parking.location.exist"));
        }
        parkingMapper.save(parking);

        String filePath = CommonVariable.PARKING_FILE_PATH + parking.getId() + ".jpg";
        try {
            parkingDTO.getImage().transferTo(new File(filePath));
        } catch (IOException e) {
            log.error("上传文件错误", e);
            throw new BusinessException(MsgUtils.get("parking.image.error"));
        }

        return parking.getName();
    }

    private Boolean isLocationExist(Double longitude, Double latitude) {
        Parking parking = parkingMapper.getByLocation(longitude, latitude);
        if (parking == null) {
            return false;
        } else {
            return true;
        }
    }

}
