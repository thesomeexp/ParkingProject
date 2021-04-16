package com.someexp.modules.user.service.impl;

import com.someexp.common.exception.BusinessException;
import com.someexp.common.utils.*;
import com.someexp.modules.user.domain.dto.UserDTO;
import com.someexp.modules.user.domain.entity.User;
import com.someexp.modules.user.domain.vo.UserVO;
import com.someexp.modules.user.mapper.UserMapper;
import com.someexp.modules.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;

/**
 * @author someexp
 * @date 2020/10/24
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 检查手机号是否注册过
     * xss过滤
     *
     * @param userDTO
     * @return
     */
    @Override
    public Long register(UserDTO userDTO) {
        if (userMapper.checkByAreaCodeAndPhone(userDTO.getAreaCode(), userDTO.getPhone())) {
            throw new BusinessException(MsgUtils.get("user.phone.exist"));
        }

        User user = new User();
        user.setName(HtmlUtils.htmlEscape(userDTO.getName()));
        user.setPassword(PasswordEncoderUtils.encode(userDTO.getPassword()));
        user.setAreaCode(userDTO.getAreaCode());
        user.setPhone(userDTO.getPhone());

        userMapper.save(user);
        return user.getId();
    }

    /**
     * 根据phone寻找用户
     * match密码
     *
     * @param userDTO
     * @return
     */
    @Override
    public String login(UserDTO userDTO) {
        User user = userMapper.getEntityByPhone(userDTO.getAreaCode(), userDTO.getPhone());
        if (user == null) {
            throw new BusinessException(MsgUtils.get("user.not.found"));
        }
        if (user.getStatus() == 0) {
            throw new BusinessException(MsgUtils.get("user.not.verify"));
        }
        if (user.getStatus() == 2) {
            throw new BusinessException(MsgUtils.get("user.has.been.lock"));
        }

        if (!PasswordEncoderUtils.matches(userDTO.getPassword(), user.getPassword())) {
            throw new BusinessException(MsgUtils.get("user.password.dont.match"));
        }

        return JwtUtils.create(user.getId(), "user");
    }

    @Override
    public UserVO getProfile() {
        User user = userMapper.getEntityById(ShiroUtils.getUserId());
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

}
