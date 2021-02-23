package com.someexp.modules.user.service;

import com.someexp.modules.user.domain.dto.ParkingDTO;
import com.someexp.modules.user.domain.entity.Parking;
import com.someexp.modules.user.domain.query.ParkingQuery;
import com.someexp.modules.user.domain.vo.ParkingVO;

import java.util.List;

/**
 * @author someexp
 * @date 2020/12/3
 */
public interface ParkingService {

    String add(ParkingDTO parkingDTO);

    List<Parking> list(ParkingQuery parkingQuery);

    /**
     * 用户查询
     *
     * @param id
     * @return
     */
    ParkingVO get(Long id);

    /**
     * 查询实体
     *
     * @param id
     * @param status
     * @return
     */
    Parking getEntity(Long id, Integer status);

    /**
     * 根据得到的temp, 更新停车场图表信息
     *
     * @param tid
     */
    void updateGraph(Long tid);

    /**
     * 检查停车场是否存在, 并通过审核
     *
     * @param id
     * @return
     */
    boolean checkParkingExists(Long id);

}
