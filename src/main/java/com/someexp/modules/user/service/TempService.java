package com.someexp.modules.user.service;

import com.someexp.modules.user.domain.dto.TempDTO;
import com.someexp.modules.user.domain.entity.Temp;
import com.someexp.modules.user.domain.vo.TempVO;

import java.util.List;

/**
 * @author someexp
 * @date 2020/12/31
 */
public interface TempService {
    /**
     * @param tempDTO 拥挤度信息
     * @return 拥挤度
     */
    Integer add(TempDTO tempDTO);

    /**
     * @param id
     * @return
     */
    Temp getEntity(Long id);

    /**
     * @return 最新5条Temp数据
     */
    List<TempVO> list(Long pid);
}
