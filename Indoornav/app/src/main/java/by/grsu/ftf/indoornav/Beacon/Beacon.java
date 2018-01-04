package by.grsu.ftf.indoornav.Beacon;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;
import java.util.List;


/**
 * Created by Vadzim on 13.11.2017.
 */

public class Beacon implements Serializable {

    private String id;
    private String RSSI;
    private String distance;
    private Float progressRSSI;
    private Float RSSIprogress;
    private String x;
    private String y;

    public Beacon() {
    }

    public Beacon(List<String> list) {
        this.id = list.get(0);
        this.distance = list.get(1);
        this.RSSI = list.get(2);
        this.progressRSSI = Float.valueOf(list.get(3));
        this.RSSIprogress = Float.valueOf(list.get(4));
        if (list.size() == 5) {
            this.x = "";
            this.y = "";
        } else {
            this.x = "X = " + list.get(5);
            this.y = "Y = " + list.get(6);
        }
    }

    public Float getRSSIprogress() {
        return RSSIprogress;
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

    public String getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x.toString();
    }

    public String getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y.toString();
    }

    public void setId(String id) {
        this.id = id;
    }
}


