package by.grsu.ftf.indoornav.Beacon;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

import by.grsu.ftf.indoornav.adapter.RSSIspeedometer;

/**
 * Created by Vadzim on 13.11.2017.
 */

public class Beacon implements Parcelable, Serializable {

    private String id;
    private String RSSI;
    private String distance;
    private Float progressRSSI;

    public Beacon(List<String> list) {
        this.id = list.get(0);
        this.distance = list.get(1);
        this.RSSI = list.get(2);
        this.progressRSSI = Float.valueOf(list.get(3));
    }

    private Beacon(Parcel parcel) {
        super();
        this.id = parcel.readString();
        this.RSSI = parcel.readString();
        this.distance = parcel.readString();
        this.progressRSSI = parcel.readFloat();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(distance);
        parcel.writeString(RSSI);
        parcel.writeFloat(progressRSSI);
    }

    public static final Parcelable.Creator<Beacon> CREATOR = new Parcelable.Creator<Beacon>(){

        @Override
        public Beacon createFromParcel(Parcel parcel) {
            return new Beacon(parcel);
        }

        @Override
        public Beacon[] newArray(int i) {
            return new Beacon[i];
        }
    };

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


