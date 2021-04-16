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

    boolean checkByPhone(String phone);

    void save(Admin adminDTO);

    List<AdminVO> listByPage(PageParamQuery pageParamQuery);

    int countByPage(PageParamQuery pageParamQuery);

    Admin get(Long id);

    void update(Admin admin);

    List<Admin> listByParent(Long parentId);
}
