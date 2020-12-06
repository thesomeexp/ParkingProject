package com.someexp.modules.admin.service.impl;

import com.someexp.common.exception.BusinessException;
import com.someexp.common.utils.JwtUtil;
import com.someexp.common.utils.MsgUtil;
import com.someexp.common.utils.PasswordEncoderUtil;
import com.someexp.modules.admin.domain.dto.AdminDTO;
import com.someexp.modules.admin.mapper.AdminMapper;
import com.someexp.modules.admin.service.AdminService;
import com.someexp.modules.user.domain.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author someexp
 * @date 2020/12/6
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    @Override
    public String login(AdminDTO adminDTO) {
        User user = adminMapper.findByPhone(adminDTO.getPhone());
        if (user == null) {
            throw new BusinessException(MsgUtil.get("admin.not.found"));
        }

        if (!PasswordEncoderUtil.matches(adminDTO.getPassword(), user.getPassword())) {
            throw new BusinessException(MsgUtil.get("admin.password.dont.match"));
        }

        String token = JwtUtil.create(user.getId());
        return token;
    }

}
