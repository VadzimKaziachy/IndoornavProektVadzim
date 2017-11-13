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
    private String angle;

    public Beacon(List<String> list) {
        this.id = list.get(0);
        this.distance = list.get(1);
        this.RSSI = list.get(2);
        this.progressRSSI = Float.valueOf(list.get(3));
        this.angle = list.get(4);
    }


    private Beacon(Parcel parcel) {
        super();
        this.id = parcel.readString();
        this.RSSI = parcel.readString();
        this.distance = parcel.readString();
        this.progressRSSI = parcel.readFloat();
        this.angle = parcel.readString();
    }

    public Integer getAngle() {
        return Integer.parseInt(angle);
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


