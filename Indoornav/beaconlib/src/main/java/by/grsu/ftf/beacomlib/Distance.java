package by.grsu.ftf.beacomlib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Вадим on 06.08.2017.
 * In this class, the distance to Beacon will be determined.
 */


class Distance {
    private ArrayList<Double> LIST_DISTANCE = new ArrayList<>();


    void determinationDistance(ArrayList<Float> LIST_RSSI) {

        List<Integer> POWER_BEACON = Arrays.asList(-65, -65, -65, -65);
        double DISTANCE;

        LIST_DISTANCE.clear();

        for (int i = 0; i < LIST_RSSI.size(); i++) {
            DISTANCE = Math.pow(10, (LIST_RSSI.get(i) - POWER_BEACON.get(i)) / ((float) -10 * 3.2));
            LIST_DISTANCE.add(DISTANCE);
        }
    }


    ArrayList<Double> getLIST_DISTANCE() {
        return LIST_DISTANCE;
    }
}
