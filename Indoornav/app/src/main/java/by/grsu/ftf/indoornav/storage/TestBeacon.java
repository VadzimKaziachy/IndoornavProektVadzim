package by.grsu.ftf.indoornav.storage;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import static by.grsu.ftf.indoornav.MainActivity.KEY_INTENT_FILTER;
import static by.grsu.ftf.indoornav.MainActivity.KEY_VALUE_LIST;

/**
 * Created by Вадим on 10.09.2017.
 */

public class TestBeacon extends Service {
    private static ArrayList<String> LIST_BEACON = new ArrayList<>();
    private static ArrayList<Float> LIST_DISTANCE = new ArrayList<>();
    private Handler mHandler = new Handler();
    private static String LIST_A = "";

    public void sortingBeacon(ArrayList<String> list) {
        if (LIST_BEACON.contains(list.get(0))) {
            int index = LIST_BEACON.indexOf(list.get(0));
            LIST_BEACON.set(index, list.get(0));
            LIST_DISTANCE.set(index, Float.valueOf(list.get(1)));
        } else {
            LIST_BEACON.add(list.get(0));
            LIST_DISTANCE.add(Float.valueOf(list.get(1)));
        }
        LIST_A = "";
        for (int i = 0; i < LIST_BEACON.size(); i++) {
            LIST_A += LIST_BEACON.get(i) + " - " + LIST_DISTANCE.get(i) + ",";
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        timeUpdaterRunnable.run();
        return super.onStartCommand(intent, flags, startId);
    }

    private Runnable timeUpdaterRunnable = new Runnable() {
        public void run() {
            Intent intent1 = new Intent(KEY_INTENT_FILTER);
            intent1.putExtra(KEY_VALUE_LIST, LIST_A);
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
