package com.someexp.modules.admin.service.impl;

import com.someexp.common.exception.BusinessException;
import com.someexp.common.utils.JwtUtils;
import com.someexp.common.utils.MsgUtils;
import com.someexp.common.utils.PasswordEncoderUtils;
import com.someexp.modules.admin.domain.dto.AdminDTO;
import com.someexp.modules.admin.domain.vo.HomeVO;
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
            throw new BusinessException(MsgUtils.get("admin.not.found"));
        }

        if (!PasswordEncoderUtils.matches(adminDTO.getPassword(), user.getPassword())) {
            throw new BusinessException(MsgUtils.get("admin.password.dont.match"));
        }

        String token = JwtUtils.create(user.getId(), "admin");
        return token;
    }

    @Override
    public HomeVO home() {
        HomeVO homeVO = new HomeVO();
        homeVO.setSum(adminMapper.countAll());
        homeVO.setVerified(adminMapper.countByStatus(1L));
        homeVO.setUnverified(adminMapper.countByStatus(0L));
        homeVO.setDisabled(adminMapper.countByStatus(2L));
        return homeVO;
    }

}
