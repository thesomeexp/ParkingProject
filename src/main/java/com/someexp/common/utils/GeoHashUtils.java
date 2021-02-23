package com.someexp.common.utils;

import ch.hsr.geohash.GeoHash;

public class GeoHashUtils {


    public static String getGeoHash(Location location) {
        return getGeoHash(location.getLongitude(), location.getLatitude(), 6);
    }

    /**
     * location --> hash
     *
     * @param longitude
     * @param latitude
     * @param characters
     * @return
     */
    public static String getGeoHash(Double longitude, Double latitude, int characters) {
        if (characters < 1 || characters > 12) {
            throw new IllegalArgumentException("'characters' must > 1 and < 12");
        }
        return GeoHash.geoHashStringWithCharacterPrecision(latitude, longitude, characters);
    }

    /**
     * 返回geohash周边8个hash加上自身
     *
     * @param geohash
     * @return
     */
    public static String[] getAdjacent(String geohash) {
        GeoHash geoHash = GeoHash.fromGeohashString(geohash);
        GeoHash[] geoHashs = geoHash.getAdjacent();
        String[] result = new String[9];
        result[0] = geohash;
        int i = 1;
        for (GeoHash g : geoHashs) {
            result[i] = g.toBase32();
            i++;
        }
        return result;
    }

}
