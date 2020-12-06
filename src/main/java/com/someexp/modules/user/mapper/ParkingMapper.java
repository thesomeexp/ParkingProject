package com.someexp.modules.user.mapper;

import com.someexp.modules.user.domain.entity.Parking;
import org.apache.ibatis.annotations.Param;

/**
 * @author someexp
 * @date 2020/12/3
 */
public interface ParkingMapper {
    void save(Parking parking);

    Parking getByLocation(@Param("longitude") Double longitude,
                          @Param("latitude") Double latitude);
}
