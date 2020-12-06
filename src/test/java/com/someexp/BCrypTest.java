package com.someexp;

import com.someexp.common.utils.PasswordEncoderUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author someexp
 * @date 2020/12/2
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BCrypTest {

    @Test
    public void generator() {
        String result = PasswordEncoderUtil.encode("333");
        System.out.println(result);

        Boolean b_result = PasswordEncoderUtil.matches("1234", result);
        System.out.println(b_result);

        b_result = PasswordEncoderUtil.matches("123", result);
        System.out.println(b_result);

    }

}
