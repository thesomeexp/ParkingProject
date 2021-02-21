package com.someexp.modules.user.service.impl;

import com.someexp.common.exception.BusinessException;
import com.someexp.common.utils.GeoHashUtils;
import com.someexp.common.utils.LocationUtils;
import com.someexp.common.utils.MsgUtils;
import com.someexp.common.utils.ShiroUtils;
import com.someexp.common.variable.CommonVariable;
import com.someexp.modules.user.domain.dto.ParkingDTO;
import com.someexp.modules.user.domain.entity.Parking;
import com.someexp.modules.user.domain.entity.Temp;
import com.someexp.modules.user.domain.query.ParkingQuery;
import com.someexp.modules.user.domain.vo.ParkingVO;
import com.someexp.modules.user.mapper.ParkingMapper;
import com.someexp.modules.user.service.ParkingService;
import com.someexp.modules.user.service.TempService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author someexp
 * @date 2020/12/3
 */
@Service
public class ParkingServiceImpl implements ParkingService {

    private static final Logger log = LoggerFactory.getLogger(ParkingServiceImpl.class);

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
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public String add(ParkingDTO parkingDTO) {
        Parking parking = new Parking();
        parking.setName(parkingDTO.getName().trim());
        parking.setContent(parkingDTO.getContent().trim());
        parking.setUid(ShiroUtils.getUserId());

        double[] xyArray = LocationUtils.parseLocation(parkingDTO.getLocation());
        // 高德地图的经纬度顺序
        double longitude = xyArray[0];
        double latitude = xyArray[1];

        parking.setLongitude(longitude);
        parking.setLatitude(latitude);
        parking.setGeohash(GeoHashUtils.getGeoHash(longitude, latitude));

        if (isLocationExist(parking.getLongitude(), parking.getLatitude())) {
            throw new BusinessException(MsgUtils.get("parking.location.exist"));
        }
        parkingMapper.save(parking);

        String filePath = CommonVariable.PARKING_FILE_PATH + parking.getId() + ".jpg";
        try {
            parkingDTO.getImage().transferTo(new File(filePath));
        } catch (IOException e) {
            log.error("上传文件错误", e);
            throw new BusinessException(MsgUtils.get("parking.image.error"));
        }

        return parking.getName();
    }

    @Override
    public List<Parking> list(ParkingQuery parkingQuery) {
        double[] xyArray = LocationUtils.parseLocation(parkingQuery.getLocation());
        // 高德地图的坐标
        double longitude = xyArray[0];
        double latitude = xyArray[1];

        String geohash = GeoHashUtils.getGeoHash(longitude, latitude);
        String[] geohashs = GeoHashUtils.getAdjacent(geohash);

        List<Parking> list = parkingMapper.list(geohashs);
        return list;
    }

    @Override
    public ParkingVO get(Long id) {
        ParkingVO parkingVO = parkingMapper.get(id);
        if (parkingVO == null) {
            throw new BusinessException(MsgUtils.get("parking.not.exist"));
        }
        return parkingVO;
    }

    @Override
    public Parking getEntity(Long id, Integer status) {
        return parkingMapper.getEntity(id, status);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateGraph(Long tid) {
        Temp temp = tempService.getEntity(tid);
        int hours = temp.getStateUpdateDate().getHours();
        String colName = 't' + String.valueOf(hours);
        parkingMapper.updateGraph(colName, temp.getState(), temp.getPid());
    }

    // todo

    private Boolean isLocationExist(Double longitude, Double latitude) {
        Parking parking = parkingMapper.getByLocation(longitude, latitude);
        if (parking == null) {
            return false;
        } else {
            return true;
        }
    }

}
