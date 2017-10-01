package by.grsu.ftf.beaconlib;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.List;

/**
 * Created by Вадим on 25.07.2017.
 * This class is the core of the module beaconlib.
 */

public class BeaconControllerService extends Service {


    Intent intent1 = new Intent("by.grsu.ftf.indoornav.KEY_INTENT_FILTER");
    public static final String KEY_VALUE_BLUTOOTH = "KEY_VALUE_BLUTOOTH";

    BeaconSimulator beaconSimulator = new BeaconSimulator();

    private final IBinder mBinder = new MyBinder();
    private Handler mHandler = new Handler();
    Callbacks activity;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        timeUpdaterRunnable.run();

        return START_NOT_STICKY;
    }


    private Runnable timeUpdaterRunnable = new Runnable() {
        public void run() {

            intent1.putExtra(KEY_VALUE_BLUTOOTH, beaconSimulator.getList());
            sendBroadcast(intent1);
            activity.updateClient(beaconSimulator.getList());
            mHandler.postDelayed(this, 100);
        }
    };

    public void registerClient(Activity activity) {
        this.activity = (Callbacks) activity;
    }


    @Override
    public void onDestroy() {
        mHandler.removeCallbacks(timeUpdaterRunnable);
        super.onDestroy();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class MyBinder extends Binder {
        public BeaconControllerService getService() {
            return BeaconControllerService.this;
        }
    }

    public interface Callbacks {
        void updateClient(List<String> list);
    }
}

