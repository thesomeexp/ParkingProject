package com.someexp.modules.admin.service.impl;

import com.someexp.common.domain.PageResultDTO;
import com.someexp.common.exception.ParamsException;
import com.someexp.common.utils.BeanUtils;
import com.someexp.common.utils.Location;
import com.someexp.common.utils.LocationUtils;
import com.someexp.common.utils.MsgUtils;
import com.someexp.modules.admin.domain.dto.ParkingUpdateDTO;
import com.someexp.modules.admin.domain.entity.Parking;
import com.someexp.modules.admin.domain.query.ParkingQuery;
import com.someexp.modules.admin.domain.vo.ParkingVO;
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
//        PageHelper.startPage(parkingQuery.getPageNum(), parkingQuery.getPageSize());
//        Page<Parking> page = (Page<Parking>) adminParkingMapper.listAll(parkingQuery);
//
//        PageResultDTO<Parking> pageResultDTO = new PageResultDTO<>();
//        BeanUtils.copyProperties(page, pageResultDTO);
//        pageResultDTO.setList(page.getResult());
        return null;
    }

    @Override
    public ParkingVO get(Long id) {
        Parking parking = adminParkingMapper.get(id);
        if (parking == null) {
            throw new ParamsException(MsgUtils.get("parking.not.exist"));
        }
        ParkingVO parkingVO = new ParkingVO();
        BeanUtils.copyProperties(parking, parkingVO);
        return parkingVO;
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

        adminParkingMapper.update(parking);
        return parking.getName();
    }
}
