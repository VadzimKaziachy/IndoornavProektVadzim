package by.grsu.ftf.beaconlib;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Вадим on 24.08.2017.
 */

class BeaconSimulator {
    private ArrayList<String> list = new ArrayList<>();

    private Random random = new Random();
    private int a3 = 0;
    private int a4 = 10;

    ArrayList<String> getList() {
        Integer id;
        Integer rssi = null;
        id = 4 - random.nextInt(4);
        switch (id) {
            case 1:
//                rssi = random.nextInt(5) - 35;
                rssi = -51;
                break;
            case 2:
//                rssi = random.nextInt(5) - 40;
                rssi = -56;
                break;
            case 3:
//                rssi = random.nextInt(5) - 50;
                rssi = -55 - a3++;
                break;
            case 4:
//                rssi = random.nextInt(5) - 45;
                rssi = -55 - a4--;
                break;
            case 5:
                rssi = random.nextInt(5) - 75;
                break;
            case 6:
                rssi = random.nextInt(5) - 60;
                break;
            case 7:
                rssi = random.nextInt(5) - 65;
                break;
            case 8:
                rssi = random.nextInt(5) - 70;
                break;
            case 9:
                rssi = random.nextInt(5) - 55;
                break;
            case 10:
                rssi = random.nextInt(5) - 80;
                break;
        }
        list.clear();
        list.add("id" + String.valueOf(id));
        list.add(String.valueOf(rssi));
        return list;
    }
}
