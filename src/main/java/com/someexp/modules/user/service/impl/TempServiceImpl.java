package com.someexp.modules.user.service.impl;

import com.someexp.common.domain.PageParamQuery;
import com.someexp.common.domain.PageResultDTO;
import com.someexp.common.domain.UserPageResultDTO;
import com.someexp.common.exception.BusinessException;
import com.someexp.common.utils.BeanUtils;
import com.someexp.common.utils.MsgUtils;
import com.someexp.common.utils.ShiroUtils;
import com.someexp.modules.user.domain.dto.TempDTO;
import com.someexp.modules.user.domain.entity.Parking;
import com.someexp.modules.user.domain.entity.Temp;
import com.someexp.modules.user.domain.vo.TempVO;
import com.someexp.modules.user.mapper.TempMapper;
import com.someexp.modules.user.service.ParkingService;
import com.someexp.modules.user.service.TempService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author someexp
 * @date 2020/12/31
 */
@Service
public class TempServiceImpl implements TempService {

    @Resource
    private TempMapper tempMapper;

    @Resource
    private ParkingService parkingService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer add(TempDTO tempDTO) throws NoSuchFieldException, IllegalAccessException {
        Parking parking = parkingService.getEntity(tempDTO.getPid(), 1);
        if (parking == null) {
            throw new BusinessException(MsgUtils.get("parking.not.exist"));
        }
        if (tempDTO.getState() > parking.getCapacity()) {
            throw new BusinessException(MsgUtils.get("temp.state.out.of.max"));
        }
        Temp temp = new Temp();
        BeanUtils.copyProperties(tempDTO, temp);
        temp.setUid(ShiroUtils.getUserId());

        if (tempMapper.checkTempExists10Min(temp.getUid(), temp.getPid())) {
            throw new BusinessException(MsgUtils.get("temp.submit.later"));
        }
        tempMapper.save(temp);
        // 更新图表信息(停车场拥挤度, 停车场空闲车位数)
        parkingService.updateGraph(temp.getId(), parking);
        return temp.getState();
    }

    @Override
    public Temp getEntity(Long id) {
        return tempMapper.getEntity(id);
    }

    @Override
    public List<TempVO> list(Long pid) {
        if (!parkingService.checkParkingExists(pid)) {
            throw new BusinessException(MsgUtils.get("parking.not.exist"));
        }
        return tempMapper.list(pid, ShiroUtils.getUserId());
    }

    @Override
    public void increase(Long tid, Integer useful) {
        if (useful == 1) {
            tempMapper.increaseUseful(tid);
        } else if (useful == -1) {
            tempMapper.increaseUnuseful(tid);
        } else {
            throw new BusinessException(MsgUtils.get("parameter.illegal", new String[]{"useful", String.valueOf(useful)}));
        }
    }

    @Override
    public Double interval(Long pid) {
        Parking parking = parkingService.getEntity(pid, 1);
        if (parking == null) {
            throw new BusinessException(MsgUtils.get("parking.not.exist"));
        }
        List<Temp> temps = tempMapper.listEntity(pid);
        if (temps.isEmpty()) {
            return -1D;
        }
        double sum = 0;
        for (Temp t : temps) {
            sum += (1 - ((double) t.getState() / (double) parking.getCapacity())) * 10;
        }
        return sum / temps.size();
    }

    @Override
    public boolean checkTempExists(Long id) {
        return tempMapper.checkTempExists(id);
    }

    @Override
    public PageResultDTO<?> listMyTemp(PageParamQuery pageParamQuery) {
        return new UserPageResultDTO<>(pageParamQuery,
                tempMapper.pageByUid(ShiroUtils.getUserId(), pageParamQuery.getOffset(), pageParamQuery.getRowCount()));
    }

}
