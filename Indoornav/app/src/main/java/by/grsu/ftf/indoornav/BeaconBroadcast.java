package by.grsu.ftf.indoornav;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;


/**
 * Created by Вадим on 22.08.2017.
 */

public class BeaconBroadcast extends BroadcastReceiver {

//BeaconBroadcast

    public static final String TAG = "Log";
    public static final String KEY_VALUE_BLUTOOTH = "KEY_VALUE_BLUTOOTH";
    private ArrayList<String> list = new ArrayList<>();



    @Override
    public void onReceive(Context context, Intent intent) {

        list = intent.getStringArrayListExtra(KEY_VALUE_BLUTOOTH);
        Log.d(TAG, list.get(0) +" "+ list.get(1));

    }
}

