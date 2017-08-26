package by.grsu.ftf.beacomlib;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Вадим on 24.08.2017.
 */

public class BeaconSimulator {
    private ArrayList<String> list = new ArrayList<>();
    private Integer id = 0;
    private Integer rssi = 0;
    private Random random = new Random();


    ArrayList<String> getList() {
        list.clear();
        rssi = random.nextInt(30) - 80;
        id = 4 - random.nextInt(4);
        list.add("id " + String.valueOf(id));
        list.add(String.valueOf(rssi));
        return list;
    }
}
