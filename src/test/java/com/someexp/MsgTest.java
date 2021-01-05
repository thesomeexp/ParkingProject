package com.someexp;

import com.someexp.common.utils.MsgUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author someexp
 * @date 2021/1/5
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MsgTest {

    @Test
    public void ObjectArrayTest() {
        System.out.println(MsgUtils.get("parameter.cant.be.null", new String[]{"useful"}));
    }

}
