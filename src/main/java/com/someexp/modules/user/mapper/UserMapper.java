package com.someexp.modules.user.mapper;

import com.someexp.modules.user.domain.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author someexp
 * @date 2020/9/22
 */
public interface UserMapper {

    User getByPhone(@Param("areaCode") Integer areaCode, @Param("phone") String phone);

    User getById(Long id);

    void save(User user);

}
