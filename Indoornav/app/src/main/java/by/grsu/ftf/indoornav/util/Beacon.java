package by.grsu.ftf.indoornav.util;

import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Вадим on 08.09.2017.
 */

public class Beacon implements Parcelable {

    private List<String> BEACON_ID = new ArrayList<>();
    private List<PointF> LIST_COORDINATE = new ArrayList<>();
    private List<Integer> POWER_BEACON = new ArrayList<>();
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

    public static final Parcelable.Creator<Beacon> CREATOR = new Parcelable.Creator<Beacon>() {

        @Override
        public Beacon createFromParcel(Parcel parcel) {
            return new Beacon(parcel);
        }

        @Override
        public Beacon[] newArray(int i) {
            return new Beacon[i];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(angle);
        parcel.writeFloat(progressRSSI);
        parcel.writeString(distance);
        parcel.writeString(RSSI);
        parcel.writeString(id);
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
