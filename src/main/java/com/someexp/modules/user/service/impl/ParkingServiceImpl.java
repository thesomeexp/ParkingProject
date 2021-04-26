package com.someexp.modules.user.service.impl;

import com.someexp.common.domain.PageParamQuery;
import com.someexp.common.domain.PageResultDTO;
import com.someexp.common.domain.UserPageResultDTO;
import com.someexp.common.exception.BusinessException;
import com.someexp.common.utils.*;
import com.someexp.common.variable.CommonVariable;
import com.someexp.modules.user.domain.dto.ParkingDTO;
import com.someexp.modules.user.domain.entity.Parking;
import com.someexp.modules.user.domain.entity.Temp;
import com.someexp.modules.user.domain.query.ParkingQuery;
import com.someexp.modules.user.domain.vo.ParkingVO;
import com.someexp.modules.user.mapper.ParkingMapper;
import com.someexp.modules.user.service.ParkingService;
import com.someexp.modules.user.service.TempService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author someexp
 * @date 2020/12/3
 */
@Slf4j
@Service
public class ParkingServiceImpl implements ParkingService {

    @Resource
    private ParkingMapper parkingMapper;

    @Resource
    private TempService tempService;

    /**
     * 检查是否存在
     * 先插入数据库, 再上传图片
     *
     * @param parkingDTO
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Long add(ParkingDTO parkingDTO) {
        Parking parking = new Parking();
        parking.setName(parkingDTO.getName().trim());
        parking.setContent(parkingDTO.getContent().trim());
        parking.setUid(ShiroUtils.getUserId());
        parking.setCapacity(parkingDTO.getCapacity());

        Location location = LocationUtils.parseLocation(parkingDTO.getLocation());
        parking.setLongitude(location.getLongitude());
        parking.setLatitude(location.getLatitude());
        parking.setGeohash(GeoHashUtils.getGeoHash(location));

        if (parkingMapper.checkByLongitudeAndLatitude(parking.getLongitude(), parking.getLatitude())) {
            throw new BusinessException(MsgUtils.get("parking.location.exist"));
        }
        parkingMapper.save(parking);

        String filePath = CommonVariable.PARKING_FILE_PATH + parking.getId() + ".jpg";
        try {
            parkingDTO.getImage().transferTo(new File(filePath));
        } catch (IOException e) {
            log.error("上传文件异常", e);
            throw new BusinessException(MsgUtils.get("parking.image.error"));
        }
        return parking.getId();
    }

    @Override
    public List<Parking> list(ParkingQuery parkingQuery) {
        Location location = LocationUtils.parseLocation(parkingQuery.getLocation());

        String geoHash = GeoHashUtils.getGeoHash(location);
        String[] geoHashes = GeoHashUtils.getAdjacent(geoHash);

        return parkingMapper.list(geoHashes);
    }

    @Override
    public ParkingVO get(Long id) {
        Parking parking = parkingMapper.getEntityByIdAndStatus(id, 1);
        if (parking == null) {
            throw new BusinessException(MsgUtils.get("parking.not.exist"));
        }
        ParkingVO parkingVO = new ParkingVO();
        BeanUtils.copyProperties(parking, parkingVO);
        parkingVO.setLocation(parkingVO.getLongitude() + "," + parkingVO.getLatitude());
        return parkingVO;
    }

    @Override
    public PageResultDTO<?> listMyParking(PageParamQuery pageParamQuery) {
        return new UserPageResultDTO<>(pageParamQuery,
                parkingMapper.pageVOByUid(ShiroUtils.getUserId(), pageParamQuery.getOffset(), pageParamQuery.getRowCount()));
    }

    @Override
    public Integer reduceFree(Long id) {
        Parking parking = parkingMapper.getEntityByIdAndStatus(id, 1);
        if (parking == null) {
            throw new BusinessException(MsgUtils.get("parking.not.exist"));
        }
        Integer newFree = Math.max((parking.getFree() - 1), 0);
        parkingMapper.updateFree(id, newFree);
        return 1;
    }

    @Override
    public Parking getEntity(Long id) {
        return parkingMapper.getEntityById(id);
    }

    @Override
    public Parking getEntity(Long id, Integer status) {
        return parkingMapper.getEntityByIdAndStatus(id, status);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateGraph(Long tid, Parking parking) throws NoSuchFieldException, IllegalAccessException {
        Temp temp = tempService.getEntity(tid, 0);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setGregorianChange(temp.getStateUpdateDate());
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        String colName = 't' + String.valueOf(hours);
        // 更新拥挤度
        Field tField = parking.getClass().getDeclaredField(colName);
        tField.setAccessible(true);
        Double oldState = Double.parseDouble(tField.get(parking).toString());
        Double newState = getParkingState(oldState, temp.getState(), parking.getCapacity());
        parkingMapper.updateState(parking.getId(), colName, newState);
        // 更新停车场空闲数 (不能超过上限)
        int newFree = (parking.getFree() + temp.getState()) >= parking.getCapacity() ? parking.getCapacity() : (parking.getFree() + temp.getState());
        parkingMapper.updateFree(parking.getId(), newFree);
    }

    /**
     * 根据数据计算新的拥挤度
     *
     * @param oldState 原始拥挤度
     * @param state    当前空闲车位数量
     * @param capacity 停车场总容量
     * @return 新的拥挤度
     */
    private double getParkingState(Double oldState, Integer state, Integer capacity) {
        double currState = 1 - (double) state / (double) capacity;
        return ((currState * 10) + oldState) / 2;
    }


    @Override
    public boolean checkParkingExists(Long id) {
        return parkingMapper.checkByIdAndStatus(id, 1);
    }

    @Override
    public void updateFree(Long id, Integer free) {
        parkingMapper.updateFree(id, free);
    }

}
