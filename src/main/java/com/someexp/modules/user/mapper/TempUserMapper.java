package com.someexp.modules.user.mapper;

import com.someexp.modules.user.domain.entity.TempUser;
import org.apache.ibatis.annotations.Param;

/**
 * @author someexp
 * @date 2021/1/5
 */
public interface TempUserMapper {

    TempUser getEntity(@Param("tid") Long tid, @Param("uid") Long uid);

    boolean checkTempUserExists(@Param("uid") Long uid, @Param("tid") Long tid);

    void save(TempUser newTempUser);

}
