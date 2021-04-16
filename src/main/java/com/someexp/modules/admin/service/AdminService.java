package com.someexp.modules.admin.service;

import com.someexp.modules.admin.domain.dto.AdminDTO;
import com.someexp.modules.admin.domain.vo.HomeVO;

/**
 * @author someexp
 * @date 2020/12/6
 */
public interface AdminService {

    /**
     * 管理员登录, 返回 JWT
     *
     * @param adminDTO
     * @return
     */
    String login(AdminDTO adminDTO);

    /**
     * 获取首页信息
     *
     * @return
     */
    HomeVO home();

}
