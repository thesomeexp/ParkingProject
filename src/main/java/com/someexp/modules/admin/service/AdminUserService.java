package com.someexp.modules.admin.service;

import com.someexp.common.domain.PageResultDTO;
import com.someexp.modules.admin.domain.dto.UserUpdateDTO;
import com.someexp.modules.admin.domain.query.UserQuery;
import com.someexp.modules.admin.domain.vo.UserVO;

/**
 * @Author someexp
 * @Date 2021/4/18
 */
public interface AdminUserService {

    PageResultDTO<?> list(UserQuery userQuery);

    UserVO get(Long id);

    Long update(Long id, UserUpdateDTO userUpdateDTO);

}
