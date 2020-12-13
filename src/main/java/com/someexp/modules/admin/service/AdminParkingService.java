package com.someexp.modules.admin.service;

import com.someexp.common.domain.PageResultDTO;
import com.someexp.modules.admin.domain.query.ParkingQuery;

/**
 * @author someexp
 * @date 2020/12/7
 */
public interface AdminParkingService {
    PageResultDTO<?> list(ParkingQuery parkingQuery);
}
