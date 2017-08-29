package by.grsu.ftf.beacomlib;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Вадим on 25.07.2017.
 * здесь будет определяться местоположение человека в помещение, данные буду приходить из класса FilterKalman
 * после чего по силе сигнала будем определять расстояние до Beacon и по трем Beacon будем определять координаты человека
 * после чего значение x и y будет отправляться в класс BeaconController, а оттуда в главный класс MainActivity
 */

class TrilateratiaBeacon {
    private ArrayList<Double> LIST_DISTANCE = new ArrayList<>();

    void setList(ArrayList<Double> LIST_DISTANCE) {

        if (this.LIST_DISTANCE.size() > 4) {
            this.LIST_DISTANCE.remove(0);
            this.LIST_DISTANCE.remove(1);
            this.LIST_DISTANCE.remove(2);
            this.LIST_DISTANCE.remove(3);
            this.LIST_DISTANCE = LIST_DISTANCE;
        } else {
            this.LIST_DISTANCE = LIST_DISTANCE;
        }

        if(this.LIST_DISTANCE.size() == 4) {
            Log.d("Log", "-- " + this.LIST_DISTANCE);
        }
    }
}
