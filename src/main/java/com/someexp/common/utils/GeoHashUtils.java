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
     * @param hash
     * @return
     */
    public static String[] getAdjacent(String hash) {
        GeoHash geoHash = GeoHash.fromGeohashString(hash);
        GeoHash[] geoHashes = geoHash.getAdjacent();
        String[] result = new String[9];
        result[0] = hash;
        int i = 1;
        for (GeoHash h : geoHashes) {
            result[i] = h.toBase32();
            i++;
        }
        return result;
    }

}
