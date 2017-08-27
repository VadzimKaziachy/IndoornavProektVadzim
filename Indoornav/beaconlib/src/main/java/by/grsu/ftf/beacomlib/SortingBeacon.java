package by.grsu.ftf.beacomlib;

import java.util.ArrayList;

/**
 * Created by Вадим on 27.08.2017.
 */

class SortingBeacon {
    private ArrayList<Integer> LIST_BEACON_ID1_RSSI = new ArrayList<>();
    private ArrayList<Integer> LIST_BEACON_ID2_RSSI = new ArrayList<>();
    private ArrayList<Integer> LIST_BEACON_ID3_RSSI = new ArrayList<>();
    private ArrayList<Integer> LIST_BEACON_ID4_RSSI = new ArrayList<>();


    private String BEACON_ID;
    private int BEACON_RSSI;
    private float RSSI;
    private float RSSI_1 = 0;
    private float RSSI_2 = 0;
    private float RSSI_3 = 0;
    private float RSSI_4 = 0;

    private FilterKalman filterKalman = new FilterKalman();


    void setList(ArrayList<String> list) {
        BEACON_ID = list.get(0);
        BEACON_RSSI = Integer.valueOf(list.get(1));
        sortingBeacon();
    }

    private void sortingBeacon() {
        switch (BEACON_ID) {
            case "id 1":
                RSSI_1 = entryBeacon(LIST_BEACON_ID1_RSSI);
                break;
            case "id 2":
                RSSI_2 = entryBeacon(LIST_BEACON_ID2_RSSI);
                break;
            case "id 3":
                RSSI_3 = entryBeacon(LIST_BEACON_ID3_RSSI);
                break;
            case "id 4":
                RSSI_4 = entryBeacon(LIST_BEACON_ID4_RSSI);
                break;
        }
    }

    private float entryBeacon(ArrayList<Integer> list) {
        if (list.size() < 6) {
            list.add(BEACON_RSSI);
        } else {
            list.remove(0);
            list.add(BEACON_RSSI);
        }
        RSSI = filterKalman.s(list);
        return RSSI;
    }
}
