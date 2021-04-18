package com.someexp.modules.admin.mapper;

import com.someexp.modules.admin.domain.entity.User;
import com.someexp.modules.admin.domain.query.UserQuery;
import com.someexp.modules.admin.domain.vo.UserVO;

import java.util.List;

/**
 * @Author someexp
 * @Date 2021/4/18
 */
public interface AdminUserMapper {

    List<UserVO> listByPage(UserQuery userQuery);

    int countByPage(UserQuery userQuery);

    UserVO getVO(Long id);

    User get(Long id);

    void update(User user);

}
