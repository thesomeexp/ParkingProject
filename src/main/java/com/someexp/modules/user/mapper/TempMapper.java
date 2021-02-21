package com.someexp.modules.user.mapper;

import com.someexp.modules.user.domain.entity.Temp;
import com.someexp.modules.user.domain.vo.TempVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author someexp
 * @date 2020/12/31
 */
public interface TempMapper {

    Temp getByUidAndPidInterval(Long uid, Long pid);

    void save(Temp temp);

    Temp get(Long id);

    List<TempVO> list(@Param("pid") Long pid, @Param("uid") Long uid);

    // todo

    List<Temp> listEntity(Long pid);

    void increaseUseful(Long tid);

    void increaseUnuseful(Long tid);

}
