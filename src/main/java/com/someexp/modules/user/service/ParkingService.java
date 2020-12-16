package com.someexp.modules.user.service;

import com.someexp.modules.user.domain.dto.ParkingDTO;
import com.someexp.modules.user.domain.query.ParkingQuery;
import com.someexp.modules.user.domain.vo.ParkingVO;

import java.util.List;

/**
 * @author someexp
 * @date 2020/12/3
 */
public interface ParkingService {

    String add(ParkingDTO parkingDTO);

    List<ParkingVO> list(ParkingQuery parkingQuery);

}
