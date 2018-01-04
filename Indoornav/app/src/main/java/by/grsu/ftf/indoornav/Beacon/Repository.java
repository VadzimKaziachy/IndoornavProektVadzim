package by.grsu.ftf.indoornav.Beacon;

import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.List;

/**
 * Created by Vadzim on 12.12.2017.
 */

public class Repository extends ViewModel {
    private List<Beacon> beacons;
    private List<Beacon> beaconCoordinate;

    public List<Beacon> getBeaconCoordinate() {
        return beaconCoordinate;
    }

    public void setBeaconCoordinate(List<Beacon> beaconCoordinate) {
        this.beaconCoordinate = beaconCoordinate;
    }

    public List<Beacon> getBeacons() {
        return beacons;
    }

    public void setBeacons(List<Beacon> beacons) {
        this.beacons = beacons;
    }
}
