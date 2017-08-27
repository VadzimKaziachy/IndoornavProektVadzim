package by.grsu.ftf.beacomlib;

import java.util.ArrayList;

/**
 * Created by Вадим on 25.07.2017.
 * сюда будут приходить данные из класса BeaconController, при случаи если тип бикана не определися, то есть не одни из сторонних SDK
 * не подходит. Сигнал будет тут усредняться и сглаживаться, чтоб не было сильных расхождение в сигнале, после чего усредненый
 * сигнал будет поступать в класс TrilateratiaBeacon где по силе сигнала будет определяться расстояние до Beacon и в дальнейшем
 * будет находиться по трем Beacon координаты местоположения человека.
 */

class FilterKalman {
    private int AMOUNT_BEACON;
    private float RSSI = 0;


    float s (ArrayList<Integer> list) {
        AMOUNT_BEACON = 0;
        for (int i = 0; i < list.size(); i++) {
            AMOUNT_BEACON += list.get(i);
        }
        RSSI = AMOUNT_BEACON / list.size();
        return RSSI;
    }
}
