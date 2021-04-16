package com.someexp.modules.user.service;

import com.someexp.common.domain.PageParamQuery;
import com.someexp.common.domain.PageResultDTO;
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

    /**
     * 返回新增停车场id
     *
     * @param parkingDTO
     * @return
     */
    Long add(ParkingDTO parkingDTO);

    List<Parking> list(ParkingQuery parkingQuery);

    /**
     * 用户查询
     *
     * @param id
     * @return
     */
    ParkingVO get(Long id);

    /**
     * 列出用户自己提交的停车场
     *
     * @return
     */
    PageResultDTO<?> listMyParking(PageParamQuery pageParamQuery);

    /**
     * 减少当前空闲停车位数量
     *
     * @param id
     * @return
     */
    Integer reduceFree(Long id);

    // (以下的供其它服务调用)

    Parking getEntity(Long id);

    /**
     * 查询实体
     *
     * @param id
     * @param status
     * @return
     */
    Parking getEntity(Long id, Integer status);

    /**
     * 根据得到的temp, 更新停车场拥挤度, 更新停车场空闲车位数
     *
     * @param tid
     */
    void updateGraph(Long tid, Parking parking) throws NoSuchFieldException, IllegalAccessException;

    /**
     * 检查停车场是否存在, 并通过审核
     *
     * @param id
     * @return
     */
    boolean checkParkingExists(Long id);

    void updateFree(Long id, Integer free);

}
