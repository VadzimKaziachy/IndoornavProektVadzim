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


    private float POWER_BEACON_1 = -65;
    private float POWER_BEACON_2 = -65;
    private float POWER_BEACON_3 = -65;
    private float POWER_BEACON_4 = -65;

    private double DISTANCE;


    void determinationDistance(ArrayList<Float> LIST_RSSI) {

        if (this.LIST_RSSI.size() > 4) {
            this.LIST_RSSI.clear();
            this.LIST_RSSI = LIST_RSSI;
        } else {
            this.LIST_RSSI = LIST_RSSI;
        }
        LIST_DISTANCE.clear();
        for (int i = 0; i < LIST_RSSI.size(); i++) {
            LIST_DISTANCE.add(distanceCalculation(LIST_RSSI.get(i), POWER_BEACON_1));
        }
    }


    private double distanceCalculation(float RSSI, float POWER) {

        DISTANCE = Math.pow(10, (RSSI - POWER) / ((float) -10 * 3.2));

        return DISTANCE;
    }

    ArrayList<Double> getLIST_DISTANCE() {
        return LIST_DISTANCE;
    }
}
