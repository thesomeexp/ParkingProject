package com.someexp.modules.user.mapper;

import com.someexp.modules.user.domain.entity.Review;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author someexp
 * @date 2021/1/7
 */
public interface ReviewMapper {

    void save(Review review);

    List<Review> pageByPid(@Param("pid") Long pid, @Param("offset") Integer offset, @Param("rowCount") Integer rowCount);

    boolean checkReviewExists(@Param("pid") Long pid, @Param("uid") Long uid);

}
