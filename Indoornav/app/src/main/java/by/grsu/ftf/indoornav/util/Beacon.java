package by.grsu.ftf.indoornav.util;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Вадим on 08.09.2017.
 */

public class Beacon {

    private List<String> BEACON_ID = new ArrayList<>();
    private List<PointF> LIST_COORDINATE = new ArrayList<>();
    private List<Integer> POWER_BEACON = new ArrayList<>();
    private String id;
    private String RSSI;
    private String distance;

    public Beacon(List<String> list) {
        this.id = list.get(0);
        this.distance = list.get(1);
        this.RSSI = list.get(2);
    }

    Beacon() {

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

    public String getId() {
        return id;
    }

    public String getRSSI() {
        return RSSI;
    }

    public String getDistance() {
        return distance;
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
