package com.someexp.modules.user.service;

import com.someexp.modules.user.domain.dto.TempUserDTO;

/**
 * @author someexp
 * @date 2021/1/5
 */
public interface TempUserService {
    /**
     * 添加 有用/无用
     *
     * @param tempUserDTO
     * @return 状态
     */
    Integer add(TempUserDTO tempUserDTO);
}
