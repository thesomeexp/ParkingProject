package com.someexp.modules.user.mapper;

import com.someexp.modules.user.domain.entity.Feedback;
import com.someexp.modules.user.domain.vo.FeedbackVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author someexp
 * @Date 2021/3/27
 */
public interface FeedbackMapper {

    void save(Feedback feedback);

    List<FeedbackVO> pageByUid(@Param("uid") Long uid, @Param("offset") Integer offset, @Param("rowCount") Integer rowCount);

}
