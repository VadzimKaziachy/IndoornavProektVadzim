package by.grsu.ftf.indoornav.util;

import android.graphics.PointF;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import by.grsu.ftf.indoornav.Beacon.Beacon;
import by.grsu.ftf.indoornav.Beacon.BeaconUtil;

/**
 * Created by Вадим on 08.09.2017.
 */

public class Distance {

    private BeaconUtil beaconUril = new BeaconUtil();

    public Beacon distanceBeacon(List<String> LIST_BEACON, List<Beacon> mCoordinate) {
        Float DISTANCE, progressRSSI, RSSIprogress;
        Float maxRSSI = -35f;
        Float minRSSI = -90f;
        Beacon beacon = new Beacon();


        String name = LIST_BEACON.get(0);
        float POWER = beaconUril.getPOWER_BEACON().get(0);

        DISTANCE = (float) Math.pow(10, (Float.valueOf(LIST_BEACON.get(1)) - POWER) / ((float) -10 * 3.2));
        progressRSSI = Math.abs((maxRSSI - Float.valueOf(LIST_BEACON.get(1))) / (maxRSSI - minRSSI));
        RSSIprogress = 240 * progressRSSI;


        beacon.setId(LIST_BEACON.get(0));
        beacon.setDistance(DISTANCE);
        beacon.setRSSI(LIST_BEACON.get(1));
        beacon.setProgressRSSI(progressRSSI);
        beacon.setRSSIprogress(RSSIprogress);

        if (mCoordinate != null) {
            for (Beacon mBeacon : mCoordinate) {
                if (mBeacon.getId().equals(name)) {
                    beacon.setX(mBeacon.getX());
                    beacon.setY(mBeacon.getY());
                    return beacon;
                } else {
                    beacon.setX(null);
                    beacon.setY(null);
                }
            }
        }else{
            beacon.setX(null);
            beacon.setY(null);
        }
        return beacon;
    }

    public List<Beacon> mCoordinate(List<Beacon> mBeacon, List<Beacon> mCoordinate) {
        if (mCoordinate != null && mBeacon != null) {
            for (Beacon beacon : mBeacon) {
                for (Beacon coordinete : mCoordinate) {
                    if (beacon.getId().equals(coordinete.getId())) {
                        beacon.setX(coordinete.getX());
                        beacon.setY(coordinete.getY());
                    }
                }
            }
        }
        return mBeacon;
    }
}
