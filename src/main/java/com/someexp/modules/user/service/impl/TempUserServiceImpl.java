package com.someexp.modules.user.service.impl;

import com.someexp.common.exception.BusinessException;
import com.someexp.common.utils.BeanUtils;
import com.someexp.common.utils.MsgUtils;
import com.someexp.common.utils.ShiroUtils;
import com.someexp.modules.user.domain.dto.TempUserDTO;
import com.someexp.modules.user.domain.entity.TempUser;
import com.someexp.modules.user.mapper.TempUserMapper;
import com.someexp.modules.user.service.TempService;
import com.someexp.modules.user.service.TempUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author someexp
 * @date 2021/1/5
 */
@Service
public class TempUserServiceImpl implements TempUserService {

    @Resource
    private TempUserMapper tempUserMapper;

    @Resource
    private TempService tempService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer add(TempUserDTO tempUserDTO) {
        if (tempUserDTO.getUseful() == 0) {
            return 0;
        }
        // 查询之前是否投票过, 有则不操作, 无则插入并更新
        Long userId = ShiroUtils.getUserId();
        TempUser tempUser = tempUserMapper.getByTidAndUid(tempUserDTO.getTid(), userId);
        if (tempUser != null) {
            return 0;
        }
        TempUser newTempUser = new TempUser();
        BeanUtils.copyProperties(tempUserDTO, newTempUser);
        newTempUser.setUid(userId);
        tempUserMapper.save(newTempUser);
        // 添加对应temp的useful
        if (newTempUser.getUseful() == 1) {
            tempService.increase(newTempUser.getTid(), 1);
        } else if (newTempUser.getUseful() == -1) {
            tempService.increase(newTempUser.getTid(), -1);
        } else {
            throw new BusinessException(MsgUtils.get("parameter.illegal", new String[]{"useful"}));
        }
        return newTempUser.getUseful();
    }
}