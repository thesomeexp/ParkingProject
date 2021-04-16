package com.someexp.modules.admin.mapper;

import com.someexp.modules.admin.domain.entity.Admin;
import com.someexp.modules.admin.domain.mapper.StatusCount;

import java.util.List;

/**
 * @author someexp
 * @date 2020/12/6
 */
public interface AdminMapper {

    Admin getByPhone(String phone);

    List<StatusCount> countParkingByStatus();

    List<StatusCount> countFeedbackByStatus();

}
