package com.someexp.modules.user.mapper;

import com.someexp.modules.user.domain.entity.User;

/**
 * @author someexp
 * @date 2020/9/22
 */
public interface UserMapper {

    User getByPhone(String phone);

    User getById(Long id);

    void save(User user);

}
