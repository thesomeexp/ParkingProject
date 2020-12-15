package com.someexp.modules.admin.service;

import com.someexp.common.domain.PageResultDTO;
import com.someexp.modules.admin.domain.dto.ParkingUpdateDTO;
import com.someexp.modules.admin.domain.query.ParkingQuery;
import com.someexp.modules.admin.domain.vo.ParkingVO;

/**
 * @author someexp
 * @date 2020/12/7
 */
public interface AdminParkingService {
    PageResultDTO<?> list(ParkingQuery parkingQuery);

    ParkingVO get(Long id);

    String update(Long id, ParkingUpdateDTO parkingUpdateDTO);
}
