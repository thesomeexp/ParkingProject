package com.someexp.fake;

import com.someexp.common.utils.GeoHashUtils;
import com.someexp.modules.user.domain.entity.Parking;
import com.someexp.modules.user.mapper.ParkingMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParkingTest {

    @Resource
    private ParkingMapper parkingMapper;

    @Test
    public void GeoHashTest() {
        for (long i = 32L; i < 50L; i++) {
            Parking parking = parkingMapper.getEntityByIdAndStatus(i, 0);
            if (parking == null)
                continue;
//            parkingMapper.updateGeoHash(GeoHashUtils.getGeoHash(parking.getLongitude(), parking.getLatitude(), 6), parking.getId());

        }
    }

    @Test
    public void generateFakeData() {
        for (int i = 0; i < 10000; i++) {
            Parking parking = new Parking();
            parking.setUid(1L);
            parking.setName("test");
            parking.setContent("testetetete");
            double lon = getRandomLongitude();
            double lat = getRandomLatitude();

            parking.setLongitude(lon);
            parking.setLatitude(lat);
            parking.setGeohash(GeoHashUtils.getGeoHash(lon, lat, 6));
            parking.setStatus(0);
            parking.setStateUpdateDate(new Date());

            try {

                parkingMapper.save(parking);
            } catch (Exception e) {
                continue;
            }

        }
    }

    public double getRandomLatitude() {
        int i = 0;
        double res = 0;
        while (i != 1) {
            Random rand = new Random();
            DecimalFormat df = new DecimalFormat("#.000000");
            int a = (int) (Math.random() * 2 + 1);
            int aa = (int) (Math.pow(-1, a));
            Double dd = Double.valueOf(df.format(rand.nextDouble() * aa));
            if (dd < 0) {
                dd = dd * -1;
            }
            dd = dd + 21;
            if (dd < 21.18 || dd > 21.27)
                continue;
            res = dd;
            i++;
        }
        return res;
    }

    public double getRandomLongitude() {
        int i = 0;
        double res = 0;
        while (i != 100) {
            Random rand = new Random();
            DecimalFormat df = new DecimalFormat("#.000000");
            int a = (int) (Math.random() * 2 + 1);
            int aa = (int) (Math.pow(-1, a));
            Double dd = Double.valueOf(df.format(rand.nextDouble() * aa));
            if (dd < 0) {
                dd = dd * -1;
            }
            dd = dd + 110;
            if (dd < 110.33 || dd > 110.45)
                continue;
            res = dd;
            i++;
        }
        return res;
    }
}
