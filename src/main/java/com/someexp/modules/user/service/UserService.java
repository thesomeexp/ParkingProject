package com.someexp.modules.user.service;

import com.someexp.modules.user.domain.dto.UserDTO;
import com.someexp.modules.user.domain.vo.UserVO;

/**
 * @author someexp
 * @date 2020/10/24
 */
public interface UserService {

    /**
     * 注册成功返回用户名
     *
     * @param userDTO
     * @return
     */
    String register(UserDTO userDTO);

    /**
     * 登录后返回jwt
     *
     * @param userDTO
     * @return
     */
    String login(UserDTO userDTO);

    UserVO getProfile();
}
