package com.someexp;

import com.someexp.common.utils.PasswordEncoderUtils;
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
        String result = PasswordEncoderUtils.encode("333");
        System.out.println(result);

        Boolean b_result = PasswordEncoderUtils.matches("1234", result);
        System.out.println(b_result);

        b_result = PasswordEncoderUtils.matches("123", result);
        System.out.println(b_result);

    }

}
