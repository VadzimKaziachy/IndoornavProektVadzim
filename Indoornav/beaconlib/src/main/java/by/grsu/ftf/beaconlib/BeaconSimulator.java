package by.grsu.ftf.beaconlib;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Вадим on 24.08.2017.
 */

class BeaconSimulator {
    private ArrayList<String> list = new ArrayList<>();

    private Random random = new Random();


    ArrayList<String> getList() {
        Integer id;
        Integer rssi;
        id = 4 - random.nextInt(4);
        rssi = random.nextInt(10) - 80;
        list.clear();
        list.add("id " + String.valueOf(id));
        list.add(String.valueOf(rssi));
        return list;
    }
}
