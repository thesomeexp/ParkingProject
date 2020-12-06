package com.someexp;

import com.someexp.common.utils.JwtUtil;
import com.someexp.modules.user.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.bind.DatatypeConverter;
import java.math.BigDecimal;

/**
 * @author someexp
 * @date 2020/9/22
 */
@Slf4j
//@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private UserMapper userMapper;

//    @Test
    public void testSelect() {
//        List<User> list = userMapper.selectList(null);
//        list.forEach(System.out::println);
    }

    @Test
    public void generateJWT() {
        String token = JwtUtil.create(1L);
        System.out.println(token);
        System.out.println(JwtUtil.decode(token));
        System.out.println(JwtUtil.getIdByToken(token));

        log.info("eihe二次 打发士");
    }

    @Test
    public void verifyJwt() {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJpZCI6MSwiZXhwIjoxNjAzNTQ3MDM1fQ.bcXbdWT_foP5KufbHN7_vQDWVGcv7tAe4i-unQAeOBrDV8RNvN2BAyeOp2n5QNOn1ZMVa-wGFDRxUX_heU9WdQ";
        System.out.println(JwtUtil.decode(token));
    }

    @Test
    public void byteTest() {
        String str = "dGhpc0lzU2VjcmV0";
        byte[] hello = DatatypeConverter.parseBase64Binary(str );
        String testStr = new String(hello);
        byte[]  world = str.getBytes();

        System.out.println("啦啦啦");

    }

    @Test
    public void switchTest() {
        int x = 2, y = 5;
        switch (x) {
            default: y++;
            case 3: y++;
            case 4: y++;
        }
        System.out.println(y);
    }

    @Test
    public void zhuTest() {
        int a = 10;
        if (a++ > 10) {
            a = 20;

        }
        System.out.println(a);
    }

}
