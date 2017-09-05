package by.grsu.ftf.beacomlib;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Вадим on 24.08.2017.
 */

class BeaconSimulator {
    private ArrayList<String> list = new ArrayList<>();

    private Integer rssi = 0;
    private Random random = new Random();


    ArrayList<String> getList() {
        Integer id;

        id = 4 - random.nextInt(4);
        switch (id) {
            case 1:
                rssi = random.nextInt(5) - 66;
                break;
            case  2:
                rssi = random.nextInt(6) - 78;
                break;
            case  3:
                rssi = random.nextInt(7) - 80;
                break;
            case  4:
                rssi = random.nextInt(7) - 93;
                break;
        }

        list.clear();
        list.add("id " + String.valueOf(id));
        list.add(String.valueOf(rssi));
        return list;
    }
}
