package by.grsu.ftf.indoornav.storage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Вадим on 10.09.2017.
 */

public class TestBeacon {
    private static ArrayList<String> LIST_BEACON = new ArrayList<>();
    private static ArrayList<Float> LIST_DISTANCE = new ArrayList<>();
    private static String LIST_A = "";

    public String sortingBeacon(List<String> list) {
        if (LIST_BEACON.contains(list.get(0))) {
            int index = LIST_BEACON.indexOf(list.get(0));
            LIST_BEACON.set(index, list.get(0));
            LIST_DISTANCE.set(index, Float.valueOf(list.get(1)));
        } else {
            LIST_BEACON.add(list.get(0));
            LIST_DISTANCE.add(Float.valueOf(list.get(1)));
        }
        LIST_A = "";
        for (int i = 0; i < LIST_BEACON.size(); i++) {
            LIST_A += LIST_BEACON.get(i) + " - " + LIST_DISTANCE.get(i) + ",";
        }
        return LIST_A;
    }
}
