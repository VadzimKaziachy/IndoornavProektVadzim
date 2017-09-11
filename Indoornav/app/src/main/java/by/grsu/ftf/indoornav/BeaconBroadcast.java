package by.grsu.ftf.indoornav;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;

import by.grsu.ftf.indoornav.util.Distance;

import static by.grsu.ftf.beaconlib.BeaconControllerService.KEY_VALUE_BLUTOOTH;


/**
 * Created by Вадим on 22.08.2017.
 * The class responsible for accepting data from the module.
 */

public class BeaconBroadcast extends BroadcastReceiver {


    public static final String TAG = BeaconBroadcast.class.getSimpleName();
    Distance distance = new Distance();


    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.hasExtra(KEY_VALUE_BLUTOOTH)) {
            ArrayList<String> list = intent.getStringArrayListExtra(KEY_VALUE_BLUTOOTH);
            Log.d(TAG, list.get(0) + " " + list.get(1));
            distance.distanceBeacon(list);
        }
    }
}

