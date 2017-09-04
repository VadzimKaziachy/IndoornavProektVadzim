package by.grsu.ftf.beacomlib;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * Created by Вадим on 25.07.2017.
 * This class is the core of the module beaconlib.
 */

public class BeaconControllerService extends Service {


    Intent intent1 = new Intent("by.grsu.ftf.indoornav.KEY_INTENT_FILTER");
    public static final String KEY_VALUE_BLUTOOTH = "KEY_VALUE_BLUTOOTH";
    public static final String KEY_VALUE_X_Y = "KEY_VALUE_X_Y";

    BeaconSimulator beaconSimulator = new BeaconSimulator();
    SortingBeacon sortingBeacon = new SortingBeacon();
    Distance distance = new Distance();
    TrilateratiaBeacon trilateratiaBeacon = new TrilateratiaBeacon();

    private Handler mHandler = new Handler();


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        timeUpdaterRunnable.run();

        return START_NOT_STICKY;
    }


    private Runnable timeUpdaterRunnable = new Runnable() {
        public void run() {

            ArrayList<String> list = beaconSimulator.getList();

            sortingBeacon.setList(list);
            distance.determinationDistance(sortingBeacon.getLIST_RSSI());
            trilateratiaBeacon.setList(distance.getLIST_DISTANCE());


            intent1.putExtra(KEY_VALUE_BLUTOOTH, list);
            intent1.putExtra(KEY_VALUE_X_Y, trilateratiaBeacon.getLIST_X_Y());
            sendBroadcast(intent1);

            mHandler.postDelayed(this, 100);
        }
    };


    @Override
    public void onDestroy() {
        mHandler.removeMessages(0);
        super.onDestroy();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}


//гид игнор
// build, idea -------- шаупрефенест
//