package com.someexp.modules.user.service.impl;

import com.someexp.common.exception.BusinessException;
import com.someexp.common.utils.BeanUtils;
import com.someexp.common.utils.MsgUtils;
import com.someexp.common.utils.ShiroUtils;
import com.someexp.modules.user.domain.dto.TempDTO;
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
    public Integer add(TempDTO tempDTO) {
        if (parkingService.getEntity(tempDTO.getPid()) == null) {
            throw new BusinessException(MsgUtils.get("parking.not.exist"));
        }
        Temp temp = new Temp();
        BeanUtils.copyProperties(tempDTO, temp);
        temp.setUid(ShiroUtils.getUserId());
        if (tempMapper.getByUidAndPidInterval(temp.getUid(), temp.getPid()) != null) {
            throw new BusinessException(MsgUtils.get("temp.submit.later"));
        }
        tempMapper.save(temp);
        // 更新图表信息
        parkingService.updateGraph(temp.getId());
        return temp.getState();
    }

    @Override
    public Temp getEntity(Long id) {
        return tempMapper.get(id);
    }

    @Override
    public List<TempVO> list(Long pid) {
        if (parkingService.getEntity(pid) == null) {
            throw new BusinessException(MsgUtils.get("parking.not.exist"));
        }
        return tempMapper.list(pid, ShiroUtils.getUserId());
    }

}
