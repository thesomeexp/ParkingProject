package com.someexp.modules.user.mapper;

import com.someexp.modules.user.domain.entity.TempUser;

/**
 * @author someexp
 * @date 2021/1/5
 */
public interface TempUserMapper {

    TempUser getByTidAndUid(Long tid, Long uid);

    void save(TempUser newTempUser);
}
