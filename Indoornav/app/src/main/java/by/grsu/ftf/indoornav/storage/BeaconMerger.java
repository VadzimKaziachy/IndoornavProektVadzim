package by.grsu.ftf.indoornav.storage;

import android.util.ArrayMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.grsu.ftf.indoornav.Beacon.Beacon;

/**
 * Created by Вадим on 10.09.2017.
 */

public class BeaconMerger {
    private Map<String, Beacon> beaconMap = new ArrayMap<>();
    private List<Beacon> beacons;
    private int position;

    public void put(Beacon beacon) {
        this.beaconMap.put(beacon.getId(), beacon);
        this.beacons = new ArrayList<>(beaconMap.values());
        position = beacons.indexOf(beacon);
    }

    public void putAll(Collection<Beacon> beacons) {
        for (Beacon beacon : beacons) {
            beaconMap.put(beacon.getId(), beacon);
        }
    }

    public int getPosition() {
        return position;
    }

    public List<Beacon> getBeacons() {
        return new ArrayList<>(beaconMap.values());
    }
}

