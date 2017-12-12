package by.grsu.ftf.indoornav.Beacon;

import android.os.Parcel;
import android.os.Parcelable;

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

    public Beacon(List<String> list) {
        this.id = list.get(0);
        this.distance = list.get(1);
        this.RSSI = list.get(2);
        this.progressRSSI = Float.valueOf(list.get(3));
        this.RSSIprogress = Float.valueOf(list.get(4));
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
}


