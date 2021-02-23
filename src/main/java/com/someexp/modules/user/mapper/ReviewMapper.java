package com.someexp.modules.user.mapper;

import com.someexp.modules.user.domain.entity.Review;

import java.util.List;

/**
 * @author someexp
 * @date 2021/1/7
 */
public interface ReviewMapper {

    void save(Review review);

    Review getByPidAndUid(Long pid, Long uid);

    List<Review> listAll(Long pid);

}
