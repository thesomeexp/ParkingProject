package com.someexp.geohash;

import com.someexp.common.utils.GeoHashUtils;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class GetHashTest {

    @Test
    public void JustTest() {
        double latitude = 21.271319;
        double longitude = 110.348673;
//        GeoHash geoHash = GeoHash.withBitPrecision(21.271319, 110.348673, 10 * 5);
//        System.out.println(geoHash.getCharacterPrecision());
//        System.out.println(geoHash.toBinaryString());
//        System.out.println("kk");
//        System.out.println("ll");
//        System.out.println(GeoHashUtils.getGeoHash(longitude, latitude, 6));
        String a[] = GeoHashUtils.getAdjacent("w7y3p0");
        for (String b : a) {
            System.out.println(b);
        }
    }
}
