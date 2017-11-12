package by.grsu.ftf.indoornav.storage;

import android.util.ArrayMap;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import by.grsu.ftf.indoornav.util.Beacon;

/**
 * Created by Вадим on 10.09.2017.
 */

public class TestBeacon {
    private List<Beacon> beacon = new ArrayList<>();
    private ArrayMap<String, Beacon> beaconMap = new ArrayMap<>();

    public void sortingBeacon(Beacon beacon) {
        this.beaconMap.put(beacon.getId(), beacon);
        this.beacon.clear();
        for (int i = 0; i < beaconMap.size(); i++) {
            this.beacon.add(beaconMap.valueAt(i));
        }
    }

    public List<Beacon> getBeacon() {
        return beacon;
    }
}
