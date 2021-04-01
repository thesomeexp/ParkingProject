package com.someexp.modules.admin.service;

import com.someexp.common.domain.PageParamQuery;
import com.someexp.common.domain.PageResultDTO;
import com.someexp.modules.admin.domain.dto.AdminDTO;
import com.someexp.modules.admin.domain.vo.AdminVO;

import java.util.List;

/**
 * @Author someexp
 * @Date 2021/4/1
 */
public interface AdminAccountService {
    Long add(AdminDTO adminDTO);

    List<AdminVO> getParent(Long id);

    PageResultDTO<?> list(PageParamQuery pageParamQuery);

    Boolean ban(AdminDTO adminDTO);
}
