package by.grsu.ftf.beacomlib;

import java.util.ArrayList;

/**
 * Created by Вадим on 25.07.2017.
 * сюда будут приходить данные из класса BeaconControllerService, при случаи если тип бикана не определися, то есть не одни из сторонних SDK
 * не подходит. Сигнал будет тут усредняться и сглаживаться, чтоб не было сильных расхождение в сигнале, после чего усредненый
 * сигнал будет поступать в класс TrilateratiaBeacon где по силе сигнала будет определяться расстояние до Beacon и в дальнейшем
 * будет находиться по трем Beacon координаты местоположения человека.
 */

class FilterKalman {

    float s (ArrayList<Integer> list) {
        int AMOUNT_BEACON;
        float RSSI;
        AMOUNT_BEACON = 0;

        for (int i = 0; i < list.size(); i++) {
            AMOUNT_BEACON += list.get(i);
        }
        RSSI = AMOUNT_BEACON / list.size();

        return RSSI;
    }
}
