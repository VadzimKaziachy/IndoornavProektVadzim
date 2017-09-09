package by.grsu.ftf.indoornav.util;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Вадим on 08.09.2017.
 */

class BeaconInfo {

    private List<String> BEACON_ID = new ArrayList<>();
    private List<PointF> LIST_COORDINATE = new ArrayList<>();
    private List<Integer> POWER_BEACON = new ArrayList<>();

    BeaconInfo() {

        BEACON_ID.add("id 1");
        BEACON_ID.add("id 2");
        BEACON_ID.add("id 3");
        BEACON_ID.add("id 4");

        LIST_COORDINATE.add(new PointF(1, 1));
        LIST_COORDINATE.add(new PointF(5, 1));
        LIST_COORDINATE.add(new PointF(1, 4));
        LIST_COORDINATE.add(new PointF(5, 4));

        POWER_BEACON.add(-65);
        POWER_BEACON.add(-65);
        POWER_BEACON.add(-65);
        POWER_BEACON.add(-65);
    }

    List<String> getBEACON_ID() {
        return BEACON_ID;
    }

    List<PointF> getLIST_COORDINATE() {
        return LIST_COORDINATE;
    }

    List<Integer> getPOWER_BEACON() {
        return POWER_BEACON;
    }
}
