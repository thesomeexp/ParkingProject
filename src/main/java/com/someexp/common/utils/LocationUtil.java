package com.someexp.common.utils;

import com.someexp.common.exception.ParamsException;
import com.someexp.common.variable.CommonVariable;

/**
 * @author someexp
 * @date 2020/12/3
 */
public class LocationUtil {

    /**
     * 解析经纬度并判断是否合法, 不合法则抛出异常
     *
     * @param location
     * @return
     */
    public static double[] parseLocation(String location) {
        try {
            double[] xyArray = parse(location);
            if (!isLocationLegal(xyArray[0], xyArray[1])) {
                throw new ParamsException("location illegal");
            }
            return xyArray;
        } catch (Exception e) {
            throw new ParamsException(MsgUtil.get("parking.location.illegal"));
        }
    }

    /**
     * 解析传入的location 返回数组
     *
     * @param location
     * @return
     */
    private static double[] parse(String location) {
        try {
            String[] locationArray = location.split(",");
            String x = locationArray[0];
            String y = locationArray[1];
            double douX = Double.parseDouble(x);
            double douY = Double.parseDouble(y);
            double[] xyArray = new double[2];
            xyArray[0] = douX;
            xyArray[1] = douY;
            return xyArray;
        } catch (Exception e) {
            throw new ParamsException(MsgUtil.get("parking.location.illegal"));
        }
    }

    /**
     * 判断定位是否在规定的范围内
     *
     * @param x
     * @param y
     * @return
     */
    private static boolean isLocationLegal(double x, double y) {
        if (x < CommonVariable.MINIMUM_X || x > CommonVariable.MAXIMUM_X ||
                y < CommonVariable.MINIMUM_Y || y > CommonVariable.MAXIMUM_Y) {
            return false;
        } else {
            return true;
        }
    }
}
