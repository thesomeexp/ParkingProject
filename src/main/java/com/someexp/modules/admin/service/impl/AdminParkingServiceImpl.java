package com.someexp.modules.admin.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.someexp.common.domain.PageResultDTO;
import com.someexp.common.utils.BeanUtils;
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
        PageHelper.startPage(parkingQuery.getPageNum(), parkingQuery.getPageSize());
        Page<Parking> page = (Page<Parking>) adminParkingMapper.listAll(parkingQuery);

        PageResultDTO<Parking> pageResultDTO = new PageResultDTO<>();
        BeanUtils.copyProperties(page, pageResultDTO);
        pageResultDTO.setList(page.getResult());
        return pageResultDTO;
    }
}
