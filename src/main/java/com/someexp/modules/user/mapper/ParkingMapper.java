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

    boolean checkByLongitudeAndLatitude(@Param("longitude") Double longitude, @Param("latitude") Double latitude);

    void save(Parking parking);

    List<Parking> list(String[] hashes);

    Parking getEntityByIdAndStatus(@Param("id") Long id, @Param("status") Integer status);

    List<ParkingVO> pageVOByUid(@Param("uid") Long uid, @Param("offset") Integer offset, @Param("rowCount") Integer rowCount);

    void updateFree(@Param("id") Long id, @Param("newFree") Integer newFree);

    boolean checkByIdAndStatus(@Param("id") Long id, @Param("status") Integer status);

    void updateState(@Param("id") Long id, @Param("colName") String colName, @Param("state") Double newState);

}
