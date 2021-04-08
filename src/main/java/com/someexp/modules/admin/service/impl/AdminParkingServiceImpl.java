package com.someexp.modules.admin.service.impl;

import com.someexp.common.domain.AdminPageResultDTO;
import com.someexp.common.domain.PageResultDTO;
import com.someexp.common.exception.ParamsException;
import com.someexp.common.utils.Location;
import com.someexp.common.utils.LocationUtils;
import com.someexp.common.utils.MsgUtils;
import com.someexp.modules.admin.domain.dto.ParkingUpdateDTO;
import com.someexp.modules.admin.domain.entity.Parking;
import com.someexp.modules.admin.domain.query.ParkingQuery;
import com.someexp.modules.admin.mapper.AdminParkingMapper;
import com.someexp.modules.admin.service.AdminParkingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author someexp
 * @date 2020/12/7
 */
@Service
public class AdminParkingServiceImpl implements AdminParkingService {

    @Resource
    private AdminParkingMapper adminParkingMapper;

    @Override
    public PageResultDTO<?> list(ParkingQuery parkingQuery) {
        return new AdminPageResultDTO<>(parkingQuery,
                adminParkingMapper.listByPage(parkingQuery),
                adminParkingMapper.countByPage(parkingQuery));
    }

    @Override
    public Parking get(Long id) {
        Parking parking = adminParkingMapper.get(id);
        if (parking == null) {
            throw new ParamsException(MsgUtils.get("parking.not.exist"));
        }
        return parking;
    }

    @Override
    public String update(Long id, ParkingUpdateDTO parkingUpdateDTO) {
        Location location = LocationUtils.parseLocation(parkingUpdateDTO.getLocation());

        Parking parking = adminParkingMapper.get(id);
        if (parking == null) {
            throw new ParamsException(MsgUtils.get("parking.not.exist"));
        }

        parking.setName(parkingUpdateDTO.getName());
        parking.setContent(parkingUpdateDTO.getContent());
        parking.setStatus(parkingUpdateDTO.getStatus());
        parking.setLongitude(location.getLongitude());
        parking.setLatitude(location.getLatitude());
        parking.setCapacity(parkingUpdateDTO.getCapacity());

        adminParkingMapper.update(parking);
        return parking.getName();
    }
}
