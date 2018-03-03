package by.grsu.ftf.indoornav.administrator.activitySearch;

import android.view.View;

import by.grsu.ftf.indoornav.db.beacon.Beacon;
import by.grsu.ftf.indoornav.db.beaconAdmin.BeaconAdmin;

/**
 * Created by Vadzim on 17.02.2018.
 */

public interface ClickListenerAdmin {
    void onItemClick(BeaconAdmin beacon, View view);
}

