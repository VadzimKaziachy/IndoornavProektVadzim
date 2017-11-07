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

    private final IBinder mBinder = new MyBinder();
    private Handler mHandler = new Handler();
    Callbacks callbacks;
    boolean serviceRun = true;

    private Runnable timeUpdaterRunnable = new Runnable() {
        public void run() {
            if(callbacks !=null){
                List<String> list_Beacon = beaconSimulator.getList();
                callbacks.updateClient(list_Beacon);
            }
            mHandler.postDelayed(this, 100);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        timeUpdaterRunnable.run();
    }

    public void registerClient(Activity resiver) {
        this.callbacks = (Callbacks) resiver;
        if (serviceRun) {
            serviceRun = false;
        }
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
        public BeaconControllerService getService() {
            return BeaconControllerService.this;
        }
    }

    public interface Callbacks {
        void updateClient(List<String> list);
    }
}

