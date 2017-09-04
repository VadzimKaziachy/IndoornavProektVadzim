package by.grsu.ftf.beacomlib;

import java.util.ArrayList;

/**
 * Created by Вадим on 25.07.2017.
 * This class will smooth RSSI, so that there are will be not strong differences.
 */

class FilterKalman {

    float s (ArrayList<Integer> list) {
        float AMOUNT_BEACON = 0;
        float RSSI;

        for (int i = 0; i < list.size(); i++) {
            AMOUNT_BEACON += list.get(i);
        }
        RSSI = AMOUNT_BEACON / list.size();

        return RSSI;
    }
}
