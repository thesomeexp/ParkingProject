package com.someexp.modules.user.mapper;

import com.someexp.modules.user.domain.entity.Parking;
import com.someexp.modules.user.domain.vo.ParkingVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author someexp
 * @date 2020/12/3
 */
public interface ParkingMapper {
    void save(Parking parking);

    Parking getByLocation(@Param("longitude") Double longitude,
                          @Param("latitude") Double latitude);

    List<ParkingVO> list(@Param("longitude") Double longitude,
                         @Param("latitude") Double latitude,
                         @Param("kilometer") Double kilometer);

    ParkingVO get(Long id);

    Parking getEntity(Long id, Integer status);

    void updateGraph(@Param("colName") String colName, @Param("state") Integer state,
                     @Param("pid") Long pid);
}
