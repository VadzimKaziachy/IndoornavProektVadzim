package by.grsu.ftf.indoornav.storage;

import android.util.ArrayMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import by.grsu.ftf.indoornav.db.Beacon;

/**
 * Created by Вадим on 10.09.2017.
 */

public class BeaconMerger {
    private Map<String, Beacon> mBeacon = new ArrayMap<>();
    private Map<Float, Beacon> beaconMap;
    private List<Beacon> beacons;
    private int position;

    public void put(Beacon beacon) {
        this.mBeacon.put(beacon.getName(), beacon);
        this.beacons = new ArrayList<>(mBeacon.values());
        position = beacons.indexOf(beacon);
    }

    public void putAll(Collection<Beacon> beacons) {
        if (beacons != null) {
            for (Beacon beacon : beacons) {
                mBeacon.put(beacon.getName(), beacon);
            }
        }
    }

    public List<Beacon> putAllBeaconMap(Collection<Beacon> beacons) {
        beaconMap = new ArrayMap<>();
        if(beacons != null) {
            for (Beacon beacon : beacons) {
                beaconMap.put(beacon.getDistance(), beacon);
            }
        }
        return new ArrayList<>(beaconMap.values());
    }

    public int getPosition() {
        return position;
    }

    public List<Beacon> getBeacons() {
        return new ArrayList<>(mBeacon.values());
    }
}

