package com.someexp.modules.user.mapper;

import com.someexp.modules.user.domain.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author someexp
 * @date 2020/9/22
 */
public interface UserMapper {

    boolean checkByAreaCodeAndPhone(@Param("areaCode") Integer areaCode, @Param("phone") String phone);

    void save(User user);

    User getEntityByPhone(@Param("areaCode") Integer areaCode, @Param("phone") String phone);

    User getEntityById(@Param("uid") Long uid);

}
