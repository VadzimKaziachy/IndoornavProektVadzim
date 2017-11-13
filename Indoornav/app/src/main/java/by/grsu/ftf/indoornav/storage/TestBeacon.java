package by.grsu.ftf.indoornav.storage;

import android.util.ArrayMap;

import java.util.ArrayList;
import java.util.List;

import by.grsu.ftf.indoornav.Beacon.Beacon;

/**
 * Created by Вадим on 10.09.2017.
 */

public class TestBeacon {
    private List<Beacon> beacon = new ArrayList<>();
    private ArrayMap<String, Beacon> beaconMap = new ArrayMap<>();

    public void sortingBeacon(Beacon beaconUtil) {
        this.beaconMap.put(beaconUtil.getId(), beaconUtil);
        this.beacon.clear();
        for (int i = 0; i < beaconMap.size(); i++) {
            this.beacon.add(beaconMap.valueAt(i));
        }
    }

    public List<Beacon> getBeacon() {
        return beacon;
    }
}
