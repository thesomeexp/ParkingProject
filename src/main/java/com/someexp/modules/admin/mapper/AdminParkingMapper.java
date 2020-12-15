package com.someexp.modules.admin.mapper;

import com.someexp.modules.admin.domain.entity.Parking;
import com.someexp.modules.admin.domain.query.ParkingQuery;

import java.util.List;

/**
 * @author someexp
 * @date 2020/12/9
 */
public interface AdminParkingMapper {

    List<Parking> listAll(ParkingQuery parkingQuery);

    Parking get(Long id);

    void update(Parking parking);
}
