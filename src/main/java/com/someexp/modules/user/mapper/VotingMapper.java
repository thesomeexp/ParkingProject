package com.someexp.modules.user.mapper;

import com.someexp.modules.user.domain.entity.Voting;
import org.apache.ibatis.annotations.Param;

/**
 * @author someexp
 * @date 2021/1/5
 */
public interface VotingMapper {

    boolean checkByUidAndTid(@Param("uid") Long uid, @Param("tid") Long tid);

    void save(Voting newVoting);

}
