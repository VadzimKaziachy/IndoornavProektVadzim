package by.grsu.ftf.indoornav.util;

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
    private List<String> beaconData = new ArrayList<>();

    public List<String> distanceBeacon(List<String> LIST_BEACON, List<Beacon> mCoordinate) {
        Float DISTANCE, progressRSSI, RSSIprogress;
        Float maxRSSI = -35f;
        Float minRSSI = -90f;

        beaconData.clear();

        String name = LIST_BEACON.get(0);
        float POWER = beaconUril.getPOWER_BEACON().get(0);

        DISTANCE = (float) Math.pow(10, (Float.valueOf(LIST_BEACON.get(1)) - POWER) / ((float) -10 * 3.2));
        progressRSSI = Math.abs((maxRSSI - Float.valueOf(LIST_BEACON.get(1))) / (maxRSSI - minRSSI));
        RSSIprogress = 240 * progressRSSI;

        beaconData.add(LIST_BEACON.get(0));
        beaconData.add(DISTANCE.toString());
        beaconData.add(LIST_BEACON.get(1));
        beaconData.add(progressRSSI.toString());
        beaconData.add(RSSIprogress.toString());

        if (mCoordinate != null) {
            for (Beacon beacon : mCoordinate) {
                if (beacon.getId().equals(name)) {
                    beaconData.add(beacon.getX());
                    beaconData.add(beacon.getY());
                    return beaconData;
                }
            }
        }
        return beaconData;
    }
}
