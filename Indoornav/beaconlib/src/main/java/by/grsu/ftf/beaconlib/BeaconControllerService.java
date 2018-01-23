package by.grsu.ftf.beaconlib;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

/**
 * Created by Вадим on 25.07.2017.
 * This class is the core of the module beaconlib.
 */

public class BeaconControllerService extends Service {

    BeaconSimulator beaconSimulator = new BeaconSimulator();

    private IBinder mBinder = new MyBinder();
    private Handler mHandler = new Handler();
    Callbacks callback;
    boolean serviceRun = true;

    private Runnable timeUpdaterRunnable = new Runnable() {
        public void run() {
            if (callback != null) {
                List<String> list_Beacon = beaconSimulator.getList();
                callback.updateClient(list_Beacon);
            }
            mHandler.postDelayed(this, 1000);
        }
    };

    @Override
    public void onCreate() {
        timeUpdaterRunnable.run();
        super.onCreate();
    }


    @Override
    public void onDestroy() {
        mHandler.removeCallbacks(timeUpdaterRunnable);
        serviceRun = true;
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class MyBinder extends Binder {
        public void connectCallbacks(Callbacks callbacks) {
            if (serviceRun) {
                callback = callbacks;
                serviceRun = false;
            }

        }
    }

    public interface Callbacks {
        void updateClient(List<String> list);
    }
}

