package com.someexp.modules.user.service.impl;

import com.someexp.common.utils.ShiroUtils;
import com.someexp.modules.user.domain.dto.TempUserDTO;
import com.someexp.modules.user.mapper.TempUserMapper;
import com.someexp.modules.user.service.TempUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author someexp
 * @date 2021/1/5
 */
@Service
public class TempUserServiceImpl implements TempUserService {

    @Resource
    private TempUserMapper tempUserMapper;

    @Override
    public Integer add(TempUserDTO tempUserDTO) {
        // 查询之前是否投票过, 有则不操作, 无则插入并更新
        Long userId = ShiroUtils.getUserId();

        return null;
    }
}
