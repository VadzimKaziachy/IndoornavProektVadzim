package by.grsu.ftf.indoornav;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;

import static by.grsu.ftf.beacomlib.BeaconControllerService.KEY_VALUE_BLUTOOTH;
import static by.grsu.ftf.beacomlib.BeaconControllerService.KEY_VALUE_X_Y;


/**
 * Created by Вадим on 22.08.2017.
 * The class responsible for accepting data from the module.
 */

public class BeaconBroadcast extends BroadcastReceiver {


    //public static final String TAG = BeaconBroadcast.class.getSimpleName();
    public static final String TAG = "Log";


    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.hasExtra(KEY_VALUE_BLUTOOTH)) {
            ArrayList<String> list = intent.getStringArrayListExtra(KEY_VALUE_BLUTOOTH);
            Log.d(TAG, list.get(0) + " " + list.get(1));
        }
        if (intent.hasExtra(KEY_VALUE_X_Y)) {
            ArrayList<String> list1 = intent.getStringArrayListExtra(KEY_VALUE_X_Y);
           Log.d(TAG,list1 + " ");
        }

    }
}

