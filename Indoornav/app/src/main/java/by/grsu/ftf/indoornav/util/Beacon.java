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
    private Float progressRSSI;
    private Float maxRSSI = -35f;
    private Float minRSSI = -90f;
    private String angle;


    public Beacon(List<Object> list) {
        this.id = (String) list.get(0);
        this.distance = list.get(1).toString();
        this.RSSI = (String) list.get(2);
        this.progressRSSI = (Float) list.get(3);
        this.angle = list.get(4).toString();
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

//    public Integer getAngle() {
//        return Integer.valueOf(angle);
//    }

    Float getMaxRSSI() {
        return maxRSSI;
    }

    Float getMinRSSI() {
        return minRSSI;
    }

    public Float getProgressRSSI() {
        return progressRSSI;
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
