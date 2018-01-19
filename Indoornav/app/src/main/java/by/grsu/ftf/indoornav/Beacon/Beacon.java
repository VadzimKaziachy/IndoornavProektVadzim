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

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public void setRSSI(String RSSI) {
        this.RSSI = RSSI;
    }

    public void setProgressRSSI(Float progressRSSI) {
        this.progressRSSI = progressRSSI;
    }

    public void setRSSIprogress(Float RSSIprogress) {
        this.RSSIprogress = RSSIprogress;
    }
}


