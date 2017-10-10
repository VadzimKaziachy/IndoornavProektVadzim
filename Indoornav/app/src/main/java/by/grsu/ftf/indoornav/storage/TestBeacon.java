package by.grsu.ftf.indoornav.storage;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Вадим on 10.09.2017.
 */

public class TestBeacon {
    private static ArrayList<String> LIST_BEACON = new ArrayList<>();
    private static ArrayList<Float> LIST_DISTANCE = new ArrayList<>();
    private static List<String> LIST = new ArrayList<>();
    private Context context;

    public TestBeacon() {

    }

    public TestBeacon(Context context) {
        this.context = context;
    }

    private void getLIST(List<String> list) {
        String beacon_list = null;
        for (int i = 0; i < list.size(); i++) {
            beacon_list = list.get(i) + ",";
        }
        Storage.setRepository(context, beacon_list);
    }

    public List<String> writeInList(String list){
        String[] massif_beacon = list.split(",");
        LIST.clear();
        for (int i = 0; i < massif_beacon.length; i++) {
            LIST.add(massif_beacon[i]);
        }
        return LIST;
    }

    public List<String> sortingBeacon(List<String> list) {
        if (LIST_BEACON.contains(list.get(0))) {
            int index = LIST_BEACON.indexOf(list.get(0));
            LIST_BEACON.set(index, list.get(0));
            LIST_DISTANCE.set(index, Float.valueOf(list.get(1)));
        } else {
            LIST_BEACON.add(list.get(0));
            LIST_DISTANCE.add(Float.valueOf(list.get(1)));
        }
        for (int i = 0; i < LIST_BEACON.size(); i++) {
            LIST.add(LIST_BEACON.get(i) + " - " + LIST_DISTANCE.get(i));
        }
        getLIST(LIST);
        return LIST;
    }
}
