package by.grsu.ftf.indoornav.persistantStorage;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import by.grsu.ftf.indoornav.MainActivity;

/**
 * Created by Вадим on 10.09.2017.
 */

public class Base extends Service{
    private static ArrayList<String> LIST_BEACON = new ArrayList<>();
    private static ArrayList<Float> LIST_DISTANCE = new ArrayList<>();
    private static ArrayList<String> LIST = new ArrayList<>();

    public static final String context = "context";


    public void sortingBeacon(ArrayList<String> list) {
        if (LIST_BEACON.contains(list.get(0))) {
            int index = LIST_BEACON.indexOf(list.get(0));
            LIST_BEACON.set(index, list.get(0));
            LIST_DISTANCE.set(index, Float.valueOf(list.get(1)));
        } else {
            LIST_BEACON.add(list.get(0));
            LIST_DISTANCE.add(Float.valueOf(list.get(1)));
        }
        LIST.clear();
        for (int i = 0; i < LIST_BEACON.size(); i++) {
            LIST.add(LIST_BEACON.get(i) + " - " + LIST_DISTANCE.get(i));
        }
        MainActivity.List(LIST);
        Storage.setMai(getBaseContext(), LIST.get(0));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
