package by.grsu.ftf.beacomlib;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * Created by Вадим on 25.07.2017.
 * этот класс получает с главного класс (MainActivity) данные, а именно сколько биканов было обнаруженно, и какого типа они
 * то есть какой фирмы, в зависимости от этого этот класс будет отправлять полученные данные в соотвествующий модуль, то есть
 * если все биканы являются Estimote тогда они отправляются в модуль Estimote в котором уже находиться соотвествующее SDK
 * также само с другими моделямя, если не один из биканов не подходит под соотвествующий SDK. Тогда он отправляеться
 * в специальный модуль (beaconlibrari) который будет сам определять расстояние до биканов, пропускать значения через фильт,
 * чтоб они сильно не изменялись, и через трилатерацию определять местоположение человека. После определения местоположение человека
 * координаты x и y будут возрашаться в этот класс после чего будут отправляться в главнный класс.
 * все выше изложенное подходит если все биканы одной фирмы, если все биканы разные, то тогда этот класс будет определять
 * какой бикан подходит под какой SDK после чего отдельно будет посылать сигнал (сигнал одного Beacon) в соотвествующий SDK
 * после чего получать от SDK расстояния до бикана, получив все 3 значения от разных SDK будет отправлять их в класс Trilateration
 * где будет высчитываться координаты человека в помещении, после чего значение координат местоположения будут отправленны
 * в этот класс, и отсюда в главный класс (MainActivity).
 */

public class BeaconController extends Service {

    private ArrayList<String> list = new ArrayList<>();

    Intent intent1 = new Intent("KEY_INTENT_FILTER");

    BeaconSimulator beaconSimulator = new BeaconSimulator();
    SortingBeacon sortingBeacon = new SortingBeacon();
    FilterKalman filterKalman = new FilterKalman();

    private Handler mHandler = new Handler();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mHandler.removeCallbacks(timeUpdaterRunnable);
        mHandler.postDelayed(timeUpdaterRunnable, 1);
        mHandler.postDelayed(sorting_beacon, 1);
        mHandler.postDelayed(jj, 1);
        return START_NOT_STICKY;

    }

    private Runnable timeUpdaterRunnable = new Runnable() {
        public void run() {
            list = beaconSimulator.getList();
            intent1.putExtra("KEY_VALUE_BLUTOOTH", list);
            sendBroadcast(intent1);
            mHandler.postDelayed(this, 1000);
        }
    };

    private Runnable sorting_beacon = new Runnable() {
        @Override
        public void run() {
            sortingBeacon.setList(list);
            mHandler.postDelayed(this, 1000);
        }
    };
    private Runnable jj = new Runnable() {
        @Override
        public void run() {

//            filterKalman.setLIST_BEACON_ID1_RSSI(sortingBeacon.getLIST_BEACON_ID1_RSSI());
//
//            filterKalman.setLIST_BEACON_ID2_RSSI(sortingBeacon.getLIST_BEACON_ID2_RSSI());
//
//            filterKalman.setLIST_BEACON_ID3_RSSI(sortingBeacon.getLIST_BEACON_ID3_RSSI());
//
//            filterKalman.setLIST_BEACON_ID4_RSSI(sortingBeacon.getLIST_BEACON_ID4_RSSI());


        }
    };


    //    void someTask() {
//        new Thread(new Runnable() {
//            public void run() {
//                while (true) {
//                    try {
//                        TimeUnit.SECONDS.sleep(1);
//                        Intent intent1 = new Intent("KEY_INTENT_FILTER");
//                        intent1.putExtra("KEY_VALUE_BLUTOOTH", beaconSimulator.getList());
//                        sendBroadcast(intent1);
//                        Log.d("Log", "отправка");
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
//    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

