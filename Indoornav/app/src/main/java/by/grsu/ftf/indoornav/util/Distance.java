package by.grsu.ftf.indoornav.util;

import java.util.ArrayList;
import java.util.List;

import by.grsu.ftf.indoornav.Beacon.BeaconUtil;

/**
 * Created by Вадим on 08.09.2017.
 */

public class Distance {
    private BeaconUtil beacon = new BeaconUtil();
    private ArrayList<String> LIST_BEACON_DISTANCE = new ArrayList<>();


    public List<String> distanceBeacon(List<String> LIST_BEACON) {
        Float DISTANCE, progressRSSI;
        Float maxRSSI = -35f;
        Float minRSSI = -90f;

        LIST_BEACON_DISTANCE.clear();

        String name = LIST_BEACON.get(0);
        Integer index = beacon.getBEACON_ID().indexOf(name);
        float POWER = beacon.getPOWER_BEACON().get(0);

        DISTANCE = (float) Math.pow(10, (Float.valueOf(LIST_BEACON.get(1)) - POWER) / ((float) -10 * 3.2));
        progressRSSI = Math.abs((maxRSSI - Float.valueOf(LIST_BEACON.get(1))) / (maxRSSI - minRSSI));

        this.LIST_BEACON_DISTANCE.add(LIST_BEACON.get(0));
        this.LIST_BEACON_DISTANCE.add(DISTANCE.toString());
        this.LIST_BEACON_DISTANCE.add(LIST_BEACON.get(1));
        this.LIST_BEACON_DISTANCE.add(progressRSSI.toString());
        return LIST_BEACON_DISTANCE;
    }
}
