package com.someexp.modules.user.service;

import com.someexp.common.domain.PageParamQuery;
import com.someexp.common.domain.PageResultDTO;
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
    Long add(TempDTO tempDTO);

    /**
     * @return 最新5条Temp数据
     */
    List<TempVO> list(Long pid);

    /**
     * 获取10分钟内的拥挤度, 如果没有结果返回-1
     *
     * @param pid
     * @return
     */
    Double interval(Long pid);

    PageResultDTO<?> listMyTemp(PageParamQuery pageParamQuery);

    // 下面是供其它服务调用的

    /**
     * @param id
     * @return
     */
    Temp getEntity(Long id, Integer status);

    /**
     * 给对应的temp添加数量
     *
     * @param tid
     * @param useful 添加有用还是无用的?
     */
    void increase(Long tid, Integer useful);

    boolean checkTempExists(Long id, Integer status);

    void resetTempTask();
    
}
