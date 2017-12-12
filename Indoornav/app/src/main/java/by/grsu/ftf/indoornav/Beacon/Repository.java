package by.grsu.ftf.indoornav.Beacon;

import android.arch.lifecycle.ViewModel;

import java.util.List;

/**
 * Created by Vadzim on 12.12.2017.
 */

public class Repository extends ViewModel {
    private List<Beacon> beacons;

    public List<Beacon> getBeacons() {
        return beacons;
    }

    public void setBeacons(List<Beacon> beacons) {
        this.beacons = beacons;
    }
}
