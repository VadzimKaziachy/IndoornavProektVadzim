package by.grsu.ftf.indoornav.adapter;

import android.view.View;

import by.grsu.ftf.indoornav.db.beacon.Beacon;

/**
 * Created by Vadzim on 28.11.2017.
 */

public interface ClickListener {
    void onItemClick(Beacon beacon, View view);
}
