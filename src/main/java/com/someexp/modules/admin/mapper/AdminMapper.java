package com.someexp.modules.admin.mapper;

import com.someexp.modules.user.domain.entity.User;

/**
 * @author someexp
 * @date 2020/12/6
 */
public interface AdminMapper {

    User findByPhone(String phone);

    Long countByStatus(Long status);

    Long countAll();
}
