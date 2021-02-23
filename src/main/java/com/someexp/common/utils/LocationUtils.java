package com.someexp.common.utils;

import com.someexp.common.exception.ParamsException;
import com.someexp.common.variable.CommonVariable;

/**
 * @author someexp
 * @date 2020/12/3
 */
public class LocationUtils {

    /**
     * 解析高德地图的坐标并判断是否合法, 不合法则抛出异常
     * xyArray[0]是longitude
     * xyArray[1]是latitude
     *
     * @param location
     * @return
     */
    public static Location parseLocation(String location) {
        try {
            Location amapLocation = parse(location);
            if (!checkLocation(amapLocation.getLongitude(), amapLocation.getLatitude())) {
                throw new ParamsException("location illegal");
            }
            return amapLocation;
        } catch (Exception e) {
            throw new ParamsException(MsgUtils.get("parking.location.illegal"));
        }
    }

    /**
     * 解析传入的location 返回数组
     * xyArray[0]是longitude
     * xyArray[1]是latitude
     *
     * @param location
     * @return
     */
    private static Location parse(String location) {
        try {
            String[] locationArray = location.split(",");
            String first = locationArray[0];
            String last = locationArray[1];
            return new AMapLocation(Double.parseDouble(first), Double.parseDouble(last));
        } catch (Exception e) {
            throw new ParamsException(MsgUtils.get("parking.location.illegal"));
        }
    }

    /**
     * 检查定位是否在规定的范围内, false不合法, true合法
     *
     * @param longitude
     * @param latitude
     * @return
     */
    private static boolean checkLocation(double longitude, double latitude) {
        if (longitude < CommonVariable.MINIMUM_LONGITUDE || longitude > CommonVariable.MAXIMUM_LONGITUDE ||
                latitude < CommonVariable.MINIMUM_LATITUDE || latitude > CommonVariable.MAXIMUM_LATITUDE) {
            return false;
        } else {
            return true;
        }
    }

    private static class AMapLocation implements Location {

        private final double longitude;

        private final double latitude;

        public AMapLocation(double longitude, double latitude) {
            this.longitude = longitude;
            this.latitude = latitude;
        }

        @Override
        public double getLongitude() {
            return this.longitude;
        }

        @Override
        public double getLatitude() {
            return this.latitude;
        }
    }

}
