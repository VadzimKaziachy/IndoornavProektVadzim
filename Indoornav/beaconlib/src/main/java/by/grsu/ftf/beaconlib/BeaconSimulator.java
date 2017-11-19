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
        Integer rssi = null;
        id = 10 - random.nextInt(10);
        switch (id) {
            case 2:
            case 5:
                rssi = random.nextInt(10) - 46;
                break;
            case 1:
            case 6:
                rssi = random.nextInt(10) - 80;
                break;
            case 3:
            case 9:
                rssi = random.nextInt(10) - 70;
                break;
            case 4:
            case 8:
                rssi = random.nextInt(10) - 56;
                break;
            case 7:
            case 10:
                rssi = random.nextInt(10) - 60;
                break;
        }
        list.clear();
        list.add("id " + String.valueOf(id));
        list.add(String.valueOf(rssi));
        return list;
    }
}
