package by.grsu.ftf.indoornav.Beacon;

import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Вадим on 08.09.2017.
 */

public class BeaconUtil {

    private List<String> BEACON_ID = new ArrayList<>();
    private List<PointF> LIST_COORDINATE = new ArrayList<>();
    private List<Integer> POWER_BEACON = new ArrayList<>();

    public BeaconUtil() {
        BEACON_ID.add("id1");
        BEACON_ID.add("id2");
        BEACON_ID.add("id3");
        BEACON_ID.add("id4");

        LIST_COORDINATE.add(new PointF(1, 1));
        LIST_COORDINATE.add(new PointF(5, 1));
        LIST_COORDINATE.add(new PointF(1, 4));
        LIST_COORDINATE.add(new PointF(5, 4));

        POWER_BEACON.add(-65);
        POWER_BEACON.add(-65);
        POWER_BEACON.add(-65);
        POWER_BEACON.add(-65);
    }

    public List<String> getBEACON_ID() {
        return BEACON_ID;
    }

    List<PointF> getLIST_COORDINATE() {
        return LIST_COORDINATE;
    }

    public List<Integer> getPOWER_BEACON() {
        return POWER_BEACON;
    }
}
