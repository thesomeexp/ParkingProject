package com.someexp.modules.admin.service.impl;

import com.someexp.common.exception.BusinessException;
import com.someexp.common.utils.JwtUtils;
import com.someexp.common.utils.MsgUtils;
import com.someexp.common.utils.PasswordEncoderUtils;
import com.someexp.modules.admin.domain.dto.AdminDTO;
import com.someexp.modules.admin.domain.entity.Admin;
import com.someexp.modules.admin.domain.mapper.StatusCount;
import com.someexp.modules.admin.domain.vo.HomeVO;
import com.someexp.modules.admin.mapper.AdminMapper;
import com.someexp.modules.admin.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
        Admin admin = adminMapper.getByPhone(adminDTO.getPhone());
        if (admin == null) {
            throw new BusinessException(MsgUtils.get("admin.not.found"));
        }
        if (!PasswordEncoderUtils.matches(adminDTO.getPassword(), admin.getPassword())) {
            throw new BusinessException(MsgUtils.get("admin.password.dont.match"));
        }
        if (admin.getStatus() != 1) {
            throw new BusinessException(MsgUtils.get("admin.account.exception"));
        }

        return JwtUtils.create(admin.getId(), "admin");
    }

    @Override
    public HomeVO home() {
        HomeVO homeVO = new HomeVO();
        List<StatusCount> parkingList = adminMapper.countParkingByStatus();
        for (StatusCount statusCount : parkingList) {
            switch (statusCount.getStatus()) {
                case 0:
                    homeVO.setParkingUnverified(statusCount.getSum());
                    break;
                case 1:
                    homeVO.setParkingVerified(statusCount.getSum());
                    break;
                case 2:
                    homeVO.setParkingDisabled(statusCount.getSum());
                    break;
            }
        }

        List<StatusCount> feedbackList = adminMapper.countFeedbackByStatus();
        for (StatusCount statusCount : feedbackList) {
            switch (statusCount.getStatus()) {
                case 0:
                    homeVO.setFeedbackUntreated(statusCount.getSum());
                    break;
                case 1:
                    homeVO.setFeedbackFinished(statusCount.getSum());
                    break;
            }
        }

        return homeVO;
    }

}
