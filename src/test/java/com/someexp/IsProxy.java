package com.someexp;

import com.someexp.config.ShiroConfig;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author someexp
 * @date 2020/11/12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IsProxy {

    @Autowired
    private ShiroConfig shiroConfig;

    @Test
    public void isShiroConfig() {
        Subject subject = SecurityUtils.getSubject();
        subject.isPermitted("hero");
        if (shiroConfig == null) {
            System.out.println("null");
        } else {
            System.out.println("not null");
        }
    }


}
