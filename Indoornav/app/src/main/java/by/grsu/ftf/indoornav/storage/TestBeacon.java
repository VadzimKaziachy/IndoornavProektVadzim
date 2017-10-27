package by.grsu.ftf.indoornav.storage;

import java.util.ArrayList;
import java.util.List;

import by.grsu.ftf.indoornav.util.Distance;

/**
 * Created by Вадим on 10.09.2017.
 */

public class TestBeacon {
    private List<String> LIST_BEACON = new ArrayList<>();
    private List<String> LIST_DISTANCE = new ArrayList<>();
    private List<String> LIST_RSSI = new ArrayList<>();
    private List<String> LIST = new ArrayList<>();
    private List<String> list = new ArrayList<>();

    private Distance distance = new Distance();


    public List<String> sortingBeacon(List<String> list1) {
        list.clear();
        list = distance.distanceBeacon(list1);
        if (LIST_BEACON.contains(list.get(0))) {
            int index = LIST_BEACON.indexOf(list.get(0));
            LIST_BEACON.set(index, list.get(0));
            LIST_DISTANCE.set(index, list.get(1));
            LIST_RSSI.set(index, list.get(2));
        } else {
            LIST_BEACON.add(list.get(0));
            LIST_DISTANCE.add(list.get(1));
            LIST_RSSI.add(list.get(2));
        }
        LIST.clear();
        for (int i = 0; i < LIST_BEACON.size(); i++) {
            LIST.add(LIST_BEACON.get(i));
            LIST.add(LIST_DISTANCE.get(i));
            LIST.add(LIST_RSSI.get(i));
        }
        return LIST;
    }
}
