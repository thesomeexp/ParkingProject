package com.someexp.modules.user.mapper;

import com.someexp.modules.user.domain.entity.User;
import com.someexp.modules.user.domain.vo.UserVO;
import org.apache.ibatis.annotations.Param;

/**
 * @author someexp
 * @date 2020/9/22
 */
public interface UserMapper {

    boolean checkUserExists(@Param("areaCode") Integer areaCode, @Param("phone") String phone);

    User getEntityByPhone(@Param("areaCode") Integer areaCode, @Param("phone") String phone);

    void save(User user);

    UserVO getById(@Param("uid") Long uid);
}
