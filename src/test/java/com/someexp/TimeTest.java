package com.someexp;

import org.junit.Test;

import java.util.Date;

/**
 * @author someexp
 * @date 2020/12/31
 */
public class TimeTest {

    @Test
    public void HourTest() {
        Date date = new Date();
        System.out.println(date.getHours());
    }

}
