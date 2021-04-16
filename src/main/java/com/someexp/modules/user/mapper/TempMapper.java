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

    void save(Temp temp);

    List<TempVO> list(@Param("pid") Long pid, @Param("uid") Long uid);

    List<Temp> listEntity(Long pid);

    List<TempVO> pageByUid(@Param("uid") Long uid, @Param("offset") Integer offset, @Param("rowCount") Integer rowCount);

    Temp getEntity(Long id, Integer status);

    void increaseUseful(Long tid);

    void increaseUnUseful(Long tid);

    boolean checkByIdAndStatus(Long id, Integer status);

    Temp getByStatus10Min(Integer status);

    void updateStatus(Long id, Integer status);

}
