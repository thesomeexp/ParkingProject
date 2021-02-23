package com.someexp.fake;

import com.someexp.modules.user.domain.entity.Review;
import com.someexp.modules.user.mapper.ReviewMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReviewTest {

    @Resource
    private ReviewMapper reviewMapper;

    @Test
    public void reviewGenerator() {
        for (long i = 33; i < 100000; i++) {
            Review review = new Review();
            review.setPid(i);
            review.setUid(666L);
            review.setScore(4);
            review.setContent("Test Test Test");
            reviewMapper.save(review);
        }
    }

    @Test
    public void reviewGenerator2() {
        for (long i = 100; i < 119; i++) {
            Review review = new Review();
            review.setPid(32L);
            review.setUid(i);
            review.setScore(4);
            review.setContent("Test Test Test");
            reviewMapper.save(review);
        }
    }
}
