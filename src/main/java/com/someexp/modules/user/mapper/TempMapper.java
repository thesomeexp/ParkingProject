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

    boolean checkTempExists10Min(Long uid, Long pid);

    boolean checkTempExists(Long id);

    void save(Temp temp);

    Temp getEntity(Long id);

    List<TempVO> list(@Param("pid") Long pid, @Param("uid") Long uid);

    void increaseUseful(Long tid);

    void increaseUnuseful(Long tid);

    List<Temp> listEntity(Long pid);

    List<TempVO> pageByUid(@Param("uid") Long uid, @Param("offset") Integer offset, @Param("rowCount") Integer rowCount);
}
