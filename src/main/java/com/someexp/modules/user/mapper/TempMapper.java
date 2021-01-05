package com.someexp.modules.user.mapper;

import com.someexp.modules.user.domain.entity.Temp;
import com.someexp.modules.user.domain.vo.TempVO;

import java.util.List;

/**
 * @author someexp
 * @date 2020/12/31
 */
public interface TempMapper {
    void save(Temp temp);

    Temp getByUidAndPidInterval(Long uid, Long pid);

    Temp get(Long id);

    List<TempVO> list(Long pid, Long uid);

    void increaseUseful(Long tid);

    void increaseUnuseful(Long tid);
}
