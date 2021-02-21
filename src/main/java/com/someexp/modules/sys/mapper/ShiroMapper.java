package com.someexp.modules.sys.mapper;

import com.someexp.modules.admin.domain.entity.Admin;
import com.someexp.modules.user.domain.entity.User;

/**
 * @author someexp
 * @date 2020/12/9
 */
public interface ShiroMapper {

    User getUserById(Long id);

    Admin getAdminById(Long userId);

}
