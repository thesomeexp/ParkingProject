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

    List<Parking> list(String[] geohashs);

    void save(Parking parking);

    boolean checkParkingExists(@Param("longitude") Double longitude,
                               @Param("latitude") Double latitude);

    ParkingVO get(Long id);

    Parking getEntity(@Param("id") Long id, @Param("status") Integer status);

    void updateGraph(@Param("colName") String colName, @Param("state") Integer state,
                     @Param("pid") Long pid);

    boolean checkParkingExistsByStatus(@Param("id") Long id, @Param("status") Integer status);

}
