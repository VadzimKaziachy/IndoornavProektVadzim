package by.grsu.ftf.beacomlib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;


/**
 * Created by Вадим on 25.07.2017.
 * здесь будет определяться местоположение человека в помещение, данные буду приходить из класса FilterKalman
 * после чего по силе сигнала будем определять расстояние до Beacon и по трем Beacon будем определять координаты человека
 * после чего значение x и y будет отправляться в класс BeaconController, а оттуда в главный класс MainActivity
 */

public class TrilateratiaBeacon extends BroadcastReceiver {
    ArrayList<String> list = new ArrayList<>();
    public static final String LOG = "LOG";


    @Override
    public void onReceive(Context context, Intent intent) {
        list = intent.getStringArrayListExtra(LOG);
setList();
    }

    public ArrayList<String> setList() {
        Log.d("Log", list.get(1));
        return list;
    }
}
