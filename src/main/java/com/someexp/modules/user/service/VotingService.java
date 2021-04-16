package com.someexp.modules.user.service;

import com.someexp.modules.user.domain.dto.VotingDTO;

/**
 * @author someexp
 * @date 2021/1/5
 */
public interface VotingService {

    /**
     * 添加 有用/无用
     *
     * @param votingDTO
     * @return 状态
     */
    Long add(VotingDTO votingDTO);

}
