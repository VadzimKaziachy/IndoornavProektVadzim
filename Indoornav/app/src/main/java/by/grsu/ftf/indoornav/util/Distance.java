package by.grsu.ftf.indoornav.util;

import java.util.ArrayList;

import by.grsu.ftf.indoornav.storage.TestBeacon;

/**
 * Created by Вадим on 08.09.2017.
 */

public class Distance {
    private BeaconInfo beaconInfo = new BeaconInfo();
    private Trilateration trilateration = new Trilateration();
    private TestBeacon base = new TestBeacon();

    private ArrayList<String> LIST_BEACON_DISTANCE = new ArrayList<>();

    public void distanceBeacon(ArrayList<String> LIST_BEACON) {
        Float DISTANCE;
        String name = LIST_BEACON.get(0);
        Integer index = beaconInfo.getBEACON_ID().indexOf(name);
        float POWER = beaconInfo.getPOWER_BEACON().get(index);
        DISTANCE = (float) Math.pow(10, (Float.valueOf(LIST_BEACON.get(1)) - POWER) / ((float) -10 * 3.2));
        this.LIST_BEACON_DISTANCE.add(LIST_BEACON.get(0));
        this.LIST_BEACON_DISTANCE.add(String.valueOf(DISTANCE));
        trilateration.sortingBeacon(LIST_BEACON_DISTANCE);
        base.sortingBeacon(LIST_BEACON_DISTANCE);

    }
}
