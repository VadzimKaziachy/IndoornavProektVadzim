package by.grsu.ftf.indoornav.db;

import android.arch.lifecycle.ViewModel;
import android.graphics.PointF;

import java.util.List;

/**
 * Created by Vadzim on 12.12.2017.
 */

public class Repository extends ViewModel {
    private List<Beacon> beacons;
    private List<Beacon> beaconCoordinate;
    private PointF deviceCoordinate;

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

    public PointF getDeviceCoordinate() {
        return deviceCoordinate;
    }

    public void setDeviceCoordinate(PointF deviceCoordinate) {
        this.deviceCoordinate = deviceCoordinate;
    }
}
