package by.grsu.ftf.beaconlib;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Вадим on 25.07.2017.
 * This class is the core of the module beaconlib.
 */

public class BeaconControllerService extends Service {


    Intent intent1 = new Intent("by.grsu.ftf.indoornav.KEY_INTENT_FILTER");
    public static final String KEY_VALUE_BLUTOOTH = "KEY_VALUE_BLUTOOTH";

    BeaconSimulator beaconSimulator = new BeaconSimulator();

    private Handler mHandler = new Handler();


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        timeUpdaterRunnable.run();

        return START_NOT_STICKY;
    }


    private Runnable timeUpdaterRunnable = new Runnable() {
        public void run() {

            intent1.putExtra(KEY_VALUE_BLUTOOTH, beaconSimulator.getList());
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

