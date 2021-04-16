package com.someexp.modules.user.service.impl;

import com.someexp.common.exception.BusinessException;
import com.someexp.common.utils.BeanUtils;
import com.someexp.common.utils.MsgUtils;
import com.someexp.common.utils.ShiroUtils;
import com.someexp.modules.user.domain.dto.VotingDTO;
import com.someexp.modules.user.domain.entity.Voting;
import com.someexp.modules.user.mapper.VotingMapper;
import com.someexp.modules.user.service.TempService;
import com.someexp.modules.user.service.VotingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author someexp
 * @date 2021/1/5
 */
@Service
public class VotingServiceImpl implements VotingService {

    @Resource
    private VotingMapper votingMapper;

    @Resource
    private TempService tempService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Long add(VotingDTO votingDTO) {
        if (!tempService.checkTempExists(votingDTO.getTid(), 0)) {
            throw new BusinessException(MsgUtils.get("common.not.found", new String[]{"temp"}));
        }
        // 查询之前是否投票过, 有则异常, 无则插入
        Long userId = ShiroUtils.getUserId();
        if (votingMapper.checkByUidAndTid(userId, votingDTO.getTid())) {
            throw new BusinessException(MsgUtils.get("voting.already.exist"));
        }
        Voting newVoting = new Voting();
        BeanUtils.copyProperties(votingDTO, newVoting);
        newVoting.setUid(userId);

        votingMapper.save(newVoting);
        // 增加对应temp的useful数量
        tempService.increase(newVoting.getTid(), newVoting.getUseful());
        return newVoting.getId();
    }

}
