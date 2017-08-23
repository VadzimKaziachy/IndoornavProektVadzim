package by.grsu.ftf.indoornav;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Вадим on 22.08.2017.
 */

public class Broadcast extends BroadcastReceiver {


    public static final String KEY_VALUE_MAPPROCESSOR = "KEY_VALUE_MAPPROCESSOR";
    public static final String KEY_VALUE_PATHCALCULATOR = "KEY_VALUE_PATHCALCULATOR";
    public static final String KEY_VALUE_BLUTOOTH = "KEY_VALUE_BLUTOOTH";


    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.hasExtra(KEY_VALUE_MAPPROCESSOR)) {
            String MAP = intent.getStringExtra(KEY_VALUE_MAPPROCESSOR);
            Log.d("Log", "MAP");
        } else if (intent.hasExtra(KEY_VALUE_PATHCALCULATOR)) {
            String PATHCALCULATOR = intent.getStringExtra(KEY_VALUE_PATHCALCULATOR);
            Log.d("Log", "PATHCALCULATOR");
        }else  if (intent.hasExtra(KEY_VALUE_BLUTOOTH)){
            String BLUETOOTH = intent.getStringExtra(KEY_VALUE_BLUTOOTH);
            Log.d("Log", "BLUETOOTH");
        }
    }
}
