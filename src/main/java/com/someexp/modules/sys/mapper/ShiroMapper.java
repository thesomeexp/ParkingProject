package com.someexp.modules.sys.mapper;

import com.someexp.modules.sys.domain.entity.User;

/**
 * @author someexp
 * @date 2020/12/9
 */
public interface ShiroMapper {

    User getById(Long id);

}
