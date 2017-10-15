package by.grsu.ftf.indoornav.storage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Вадим on 10.09.2017.
 */

public class TestBeacon {
    public static ArrayList<String> LIST_BEACON = new ArrayList<>();
    public static ArrayList<String> LIST_DISTANCE = new ArrayList<>();
    private static List<String> LIST = new ArrayList<>();

    public void sortingBeacon(List<String> list) {
        if (LIST_BEACON.contains(list.get(0))) {
            int index = LIST_BEACON.indexOf(list.get(0));
            LIST_BEACON.set(index, list.get(0));
            LIST_DISTANCE.set(index, list.get(1));
        } else {
            LIST_BEACON.add(list.get(0));
            LIST_DISTANCE.add(list.get(1));
        }
        for (int i = 0; i < LIST_BEACON.size(); i++) {
            LIST.add(LIST_BEACON.get(i) + " - " + LIST_DISTANCE.get(i));
        }
    }
}
