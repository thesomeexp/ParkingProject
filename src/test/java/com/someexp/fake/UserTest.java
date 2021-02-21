package com.someexp.fake;

import com.someexp.modules.user.domain.entity.User;
import com.someexp.modules.user.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void fakeDataGenerate() {
        User user = new User();
        user.setName("test");
        user.setPassword("testtesttesttesttesttest");
        user.setAreaCode(66);
        for (int i = 11; i < 100000; i++) {
            user.setPhone(i + "");
            userMapper.save(user);
        }
    }

}
