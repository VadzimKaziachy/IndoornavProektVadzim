package by.grsu.ftf.indoornav.util;

import android.util.Log;

import java.util.List;

import by.grsu.ftf.indoornav.db.Beacon;

/**
 * Created by Вадим on 08.09.2017.
 */

public class Distance {
//    int a = 0;

    public Beacon distanceBeacon(List<String> LIST_BEACON, List<Beacon> mCoordinate) {
        Float DISTANCE, progressRSSI, RSSIprogress;
        Float maxRSSI = -35f;
        Float minRSSI = -90f;
        Beacon beacon = new Beacon();


        String name = LIST_BEACON.get(0);
        Integer id = Integer.parseInt(name.substring(2));
//        Integer id = a++;
        float POWER = -65;

        DISTANCE = (float) Math.pow(10, (Float.valueOf(LIST_BEACON.get(1)) - POWER) / ((float) -10 * 3.2));
        progressRSSI = Math.abs((maxRSSI - Float.valueOf(LIST_BEACON.get(1))) / (maxRSSI - minRSSI));
        RSSIprogress = 240 * progressRSSI;

        Log.d("Log", name + " = " + LIST_BEACON.get(1) + " = " + DISTANCE);
        beacon.setId(id);
        beacon.setName(name);
        beacon.setDistance(DISTANCE);
        beacon.setRSSI(LIST_BEACON.get(1));
        beacon.setProgressRSSI(progressRSSI);
        beacon.setRSSIprogress(RSSIprogress);

        if (mCoordinate != null) {
            for (Beacon mBeacon : mCoordinate) {
                if (mBeacon.getName().equals(name)) {
                    beacon.setX(mBeacon.getX());
                    beacon.setY(mBeacon.getY());
                    return beacon;
                } else {
                    beacon.setX(null);
                    beacon.setY(null);
                }
            }
        } else {
            beacon.setX(null);
            beacon.setY(null);
        }
        return beacon;
    }

    public List<Beacon> mCoordinate(List<Beacon> mBeacon, List<Beacon> mCoordinate) {
        if (mCoordinate != null && mBeacon != null) {
            for (Beacon beacon : mBeacon) {
                for (Beacon coordinete : mCoordinate) {
                    if (beacon.getName().equals(coordinete.getName())) {
                        beacon.setX(coordinete.getX());
                        beacon.setY(coordinete.getY());
                    }
                }
            }
        }
        return mBeacon;
    }
}
