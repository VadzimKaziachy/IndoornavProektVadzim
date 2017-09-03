package by.grsu.ftf.beacomlib;

import java.util.ArrayList;

/**
 * Created by Вадим on 06.08.2017.
 * в этом классе будет определяться расстояние до Beacon, сейчас я решил пропустить класс TypeBeacon так как у нас пока есть
 * тока один тип Beacon
 */


class Distance {
    private ArrayList<Float> LIST_RSSI = new ArrayList<>();
    private ArrayList<Double> LIST_DISTANCE = new ArrayList<>();


    void determinationDistance(ArrayList<Float> LIST_RSSI) {
        float POWER_BEACON_1 = -65;
        float POWER_BEACON_2 = -65;
        float POWER_BEACON_3 = -65;
        float POWER_BEACON_4 = -65;
        double DISTANCE;
//        Log.d("Log", LIST_RSSI + " ");
        if (this.LIST_RSSI.size() > 4) {
            this.LIST_RSSI.clear();
            this.LIST_RSSI = LIST_RSSI;
        } else {
            this.LIST_RSSI = LIST_RSSI;
        }
        LIST_DISTANCE.clear();

        for (int i = 0; i < this.LIST_RSSI.size(); i++) {
            DISTANCE = Math.pow(10, (this.LIST_RSSI.get(i) - POWER_BEACON_1) / ((float) -10 * 3.2));
            LIST_DISTANCE.add(DISTANCE);
        }
    }


    ArrayList<Double> getLIST_DISTANCE() {
        return LIST_DISTANCE;
    }
}
