package com.someexp.modules.admin.mapper;

import com.someexp.common.domain.PageParamQuery;
import com.someexp.modules.admin.domain.entity.Admin;
import com.someexp.modules.admin.domain.vo.AdminVO;

import java.util.List;

/**
 * @Author someexp
 * @Date 2021/4/1
 */
public interface AdminAccountMapper {

    void save(Admin adminDTO);

    Admin getByPhone(String phone);

    Admin get(Long id);

    List<AdminVO> listByPage(PageParamQuery pageParamQuery);

    int countByPage(PageParamQuery pageParamQuery);

    void update(Admin admin);

    List<Admin> listByParent(Long parentId);
}
