package com.someexp.modules.admin.service.impl;

import com.someexp.common.domain.AdminPageResultDTO;
import com.someexp.common.domain.PageResultDTO;
import com.someexp.common.exception.ParamsException;
import com.someexp.common.utils.BeanUtils;
import com.someexp.common.utils.MsgUtils;
import com.someexp.modules.admin.domain.dto.UserUpdateDTO;
import com.someexp.modules.admin.domain.entity.User;
import com.someexp.modules.admin.domain.query.UserQuery;
import com.someexp.modules.admin.domain.vo.UserVO;
import com.someexp.modules.admin.mapper.AdminUserMapper;
import com.someexp.modules.admin.service.AdminUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author someexp
 * @Date 2021/4/18
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Resource
    private AdminUserMapper adminUserMapper;

    @Override
    public PageResultDTO<?> list(UserQuery userQuery) {
        return new AdminPageResultDTO<>(userQuery,
                adminUserMapper.listByPage(userQuery),
                adminUserMapper.countByPage(userQuery));
    }

    @Override
    public UserVO get(Long id) {
        UserVO userVO = adminUserMapper.getVO(id);
        if (userVO == null) {
            throw new ParamsException(MsgUtils.get("user.not.exist"));
        }
        return userVO;
    }

    @Override
    public Long update(Long id, UserUpdateDTO userUpdateDTO) {
        User user = adminUserMapper.get(id);
        if (user == null) {
            throw new ParamsException(MsgUtils.get("user.not.exist"));
        }
        BeanUtils.copyProperties(userUpdateDTO, user);
        adminUserMapper.update(user);
        return user.getId();
    }

}
