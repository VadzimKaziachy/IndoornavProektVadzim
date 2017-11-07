package by.grsu.ftf.indoornav.util;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Вадим on 08.09.2017.
 */

public class Distance {
    private Beacon beacon = new Beacon();
    private ArrayList<Object> LIST_BEACON_DISTANCE = new ArrayList<>();

    public List<Object> distanceBeacon(List<String> LIST_BEACON) {
        Float DISTANCE, progressRSSI;

        LIST_BEACON_DISTANCE.clear();
        String name = LIST_BEACON.get(0);
        Integer index = beacon.getBEACON_ID().indexOf(name);
        float POWER = beacon.getPOWER_BEACON().get(0);
        DISTANCE = (float) Math.pow(10, (Float.valueOf(LIST_BEACON.get(1)) - POWER) / ((float) -10 * 3.2));
        progressRSSI = Math.abs((beacon.getMaxRSSI() - Float.valueOf(LIST_BEACON.get(1)))
                / (beacon.getMaxRSSI() - beacon.getMinRSSI()));
        String angle = String.valueOf(180 * progressRSSI);
        this.LIST_BEACON_DISTANCE.add(LIST_BEACON.get(0));
        this.LIST_BEACON_DISTANCE.add(DISTANCE);
        this.LIST_BEACON_DISTANCE.add(LIST_BEACON.get(1));
        this.LIST_BEACON_DISTANCE.add(progressRSSI);
        this.LIST_BEACON_DISTANCE.add(angle);
        return LIST_BEACON_DISTANCE;
    }
}
