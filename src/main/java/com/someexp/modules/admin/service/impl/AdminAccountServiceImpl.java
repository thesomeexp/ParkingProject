package com.someexp.modules.admin.service.impl;

import com.someexp.common.domain.AdminPageResultDTO;
import com.someexp.common.domain.PageParamQuery;
import com.someexp.common.domain.PageResultDTO;
import com.someexp.common.exception.BusinessException;
import com.someexp.common.utils.BeanUtils;
import com.someexp.common.utils.MsgUtils;
import com.someexp.common.utils.PasswordEncoderUtils;
import com.someexp.common.utils.ShiroUtils;
import com.someexp.modules.admin.domain.dto.AdminDTO;
import com.someexp.modules.admin.domain.entity.Admin;
import com.someexp.modules.admin.domain.vo.AdminVO;
import com.someexp.modules.admin.mapper.AdminAccountMapper;
import com.someexp.modules.admin.service.AdminAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author someexp
 * @Date 2021/4/1
 */
@Slf4j
@Service
public class AdminAccountServiceImpl implements AdminAccountService {

    @Resource
    private AdminAccountMapper adminAccountMapper;

    @Override
    public Long add(AdminDTO adminDTO) {
        if (adminAccountMapper.checkByPhone(adminDTO.getPhone())) {
            throw new BusinessException(MsgUtils.get("admin.account.exist"));
        }
        Admin newAdmin = new Admin();
        BeanUtils.copyProperties(adminDTO, newAdmin);
        newAdmin.setParent(ShiroUtils.getUserId());
        newAdmin.setPassword(PasswordEncoderUtils.encode(adminDTO.getPassword()));
        adminAccountMapper.save(newAdmin);
        return newAdmin.getId();
    }

    @Override
    public PageResultDTO<?> list(PageParamQuery pageParamQuery) {
        return new AdminPageResultDTO<>(pageParamQuery,
                adminAccountMapper.listByPage(pageParamQuery),
                adminAccountMapper.countByPage(pageParamQuery));
    }

    @Override
    public List<AdminVO> getParent(Long id) {
        List<AdminVO> result = new ArrayList<>();
        Admin curr = adminAccountMapper.get(id);
        while (curr != null) {
            try {
                AdminVO newAdminVO = new AdminVO();
                BeanUtils.copyProperties(curr, newAdminVO);
                result.add(newAdminVO);
                curr = adminAccountMapper.get(curr.getParent());
            } catch (Exception e) {
                log.error("获取管理员父类异常", e);
                throw new BusinessException("获取管理员父类异常");
            }
        }
        Collections.reverse(result);
        return result;
    }

    @Override
    public Boolean ban(AdminDTO adminDTO) {
        Long id = ShiroUtils.getUserId();
        if (adminDTO.getId() == id) {
            throw new BusinessException(MsgUtils.get("admin.cant.ban.yourself"));
        }
        checkPermit(id, adminDTO.getId());

        Admin curr = adminAccountMapper.get(adminDTO.getId());
        if (curr == null) {
            throw new BusinessException(MsgUtils.get("admin.not.found"));
        }
        if (adminDTO.getIsRelated()) {
            // 封禁账号以及其子账号
            Queue<Long> delQueue = new LinkedList<>();
            delQueue.offer(curr.getId());
            while (!delQueue.isEmpty()) {
                try {
                    curr = adminAccountMapper.get(delQueue.poll());
                    List<Admin> list = adminAccountMapper.listByParent(curr.getId());
                    for (Admin a : list) {
                        delQueue.offer(a.getId());
                    }
                    Admin admin = new Admin();
                    admin.setId(curr.getId());
                    admin.setStatus(adminDTO.getStatus());
                    adminAccountMapper.update(admin);
                } catch (Exception e) {
                    log.error("获取管理员父类异常", e);
                    curr = null;
                    throw new BusinessException("获取管理员父类异常");
                }
            }

        } else {
            // 只封禁当前账号
            Admin admin = new Admin();
            admin.setId(curr.getId());
            admin.setStatus(adminDTO.getStatus());
            adminAccountMapper.update(admin);
        }
        return true;
    }

    private Boolean checkPermit(Long operator, Long target) {
        Admin curr = adminAccountMapper.get(target);
        while (curr != null) {
            try {
                if (curr.getId() == operator) {
                    return true;
                }
                curr = adminAccountMapper.get(curr.getParent());
            } catch (Exception e) {
                log.error("获取管理员父类异常", e);
                curr = null;
                throw new BusinessException("获取管理员父类异常");
            }
        }
        throw new BusinessException(MsgUtils.get("admin.permission.denied"));
    }
}
