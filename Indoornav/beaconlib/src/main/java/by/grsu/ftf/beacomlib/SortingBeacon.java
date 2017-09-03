package by.grsu.ftf.beacomlib;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Вадим on 27.08.2017.
 */

class SortingBeacon {

    private ArrayList<Integer> LIST_BEACON_ID1_RSSI = new ArrayList<>();
    private ArrayList<Integer> LIST_BEACON_ID2_RSSI = new ArrayList<>();
    private ArrayList<Integer> LIST_BEACON_ID3_RSSI = new ArrayList<>();
    private ArrayList<Integer> LIST_BEACON_ID4_RSSI = new ArrayList<>();
    private ArrayList<Float> LIST_RSSI = new ArrayList<>();
    private ArrayList<Float> LIST = new ArrayList<>();


    private int BEACON_RSSI;
    private float RSSI_1 = 0;
    private float RSSI_2 = 0;
    private float RSSI_3 = 0;
    private float RSSI_4 = 0;
    private int i1 = 0;
    private int i2 = 0;
    private int i3 = 0;
    private int i4 = 0;



    private FilterKalman filterKalman = new FilterKalman();


    void setList(ArrayList<String> list) {
        String BEACON_ID;

        BEACON_ID = list.get(0);
        BEACON_RSSI = Integer.valueOf(list.get(1));

        switch (BEACON_ID) {
            case "id 1":
                if(i1!=0){
                    LIST.remove(0);
                    RSSI_1 = entryBeacon(LIST_BEACON_ID1_RSSI);
                    LIST.add(RSSI_1);
                } else {
                    RSSI_1 = entryBeacon(LIST_BEACON_ID1_RSSI);
                    LIST.add(RSSI_1);
                    i1 = 1;
                }
                break;
            case "id 2":
                if(i2!=0){
                    LIST.remove(0);
                    RSSI_2 = entryBeacon(LIST_BEACON_ID2_RSSI);
                    LIST.add(RSSI_1);
                } else {
                    RSSI_2 = entryBeacon(LIST_BEACON_ID2_RSSI);
                    LIST.add(RSSI_2);
                    i2 = 1;
                }
                break;
            case "id 3":
                if(i3!=0){
                    LIST.remove(0);
                    RSSI_3 = entryBeacon(LIST_BEACON_ID3_RSSI);
                    LIST.add(RSSI_3);
                } else {
                    RSSI_3 = entryBeacon(LIST_BEACON_ID3_RSSI);
                    LIST.add(RSSI_3);
                    i3 = 1;
                }
                break;
            case "id 4":
                if(i4!=0){
                    LIST.remove(0);
                    RSSI_4 = entryBeacon(LIST_BEACON_ID4_RSSI);
                    LIST.add(RSSI_4);
                } else {
                    RSSI_4 = entryBeacon(LIST_BEACON_ID4_RSSI);
                    LIST.add(RSSI_4);
                    i4 = 1;
                }
                break;
        }

        int q = Collections.frequency(LIST, 0);
        if ((LIST.size() - q) > 2) {
            LIST_RSSI.clear();
            LIST_RSSI.add(RSSI_1);
            LIST_RSSI.add(RSSI_2);
            LIST_RSSI.add(RSSI_3);
            LIST_RSSI.add(RSSI_4);
        }
    }


    private float entryBeacon(ArrayList<Integer> list) {
        float RSSI;
        if (list.size() < 6) {
            list.add(BEACON_RSSI);
        } else {
            list.remove(0);
            list.add(BEACON_RSSI);
        }
        RSSI = filterKalman.s(list);
        return RSSI;
    }


    ArrayList<Float> getLIST_RSSI() {
        return LIST_RSSI;
    }
}

