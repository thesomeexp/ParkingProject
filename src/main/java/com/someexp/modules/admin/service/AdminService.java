package com.someexp.modules.admin.service;

import com.someexp.modules.admin.domain.dto.AdminDTO;
import com.someexp.modules.admin.domain.vo.HomeVO;

/**
 * @author someexp
 * @date 2020/12/6
 */
public interface AdminService {

    String login(AdminDTO adminDTO);

    HomeVO home();
}
