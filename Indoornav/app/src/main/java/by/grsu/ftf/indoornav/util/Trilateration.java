package by.grsu.ftf.indoornav.util;

import android.graphics.PointF;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Вадим on 25.07.2017.
 * In this class, the coordinates of the person's location in the room will be determined, in cases where all Beacons are different,
 * if all Beacons are the same (that is ones firm), then this class will not be used.
 */

class Trilateration {

    public static final String TAG = Trilateration.class.getSimpleName();

    private static ArrayList<String> LIST_BEACON = new ArrayList<>();
    private static ArrayList<Float> LIST_DISTANCE = new ArrayList<>();

    private ArrayList<Float> LIST_DISTANCE_BEACON = new ArrayList<>();
    private ArrayList<Float> LIST = new ArrayList<>();
    private ArrayList<PointF> LIST_COORDINATE = new ArrayList<>();
    private float a;

    private BeaconInfo beaconInfo = new BeaconInfo();

    void sortingBeacon(ArrayList<String> list) {
        if (LIST_BEACON.contains(list.get(0))) {
            int index = LIST_BEACON.indexOf(list.get(0));
            LIST_BEACON.set(index, list.get(0));
            LIST_DISTANCE.set(index, Float.valueOf(list.get(1)));
        } else {
            LIST_BEACON.add(list.get(0));
            LIST_DISTANCE.add(Float.valueOf(list.get(1)));
        }
        if (LIST_BEACON.size() > 2) {
            for (int i = 0; i < LIST_DISTANCE.size(); i++) {
                this.LIST.add(LIST_DISTANCE.get(i));
            }
            for (int j = 0; j < LIST.size() - 3; j++) {
                for (int i = 0; i < LIST.size() - 1; i++) {
                    if (i == 0) {
                        a = Math.max(LIST.get(i), LIST.get(i + 1));
                    } else {
                        a = Math.max(a, LIST.get(i + 1));
                    }
                }
                LIST.set(LIST.indexOf(a), (float) 0);
            }
            LIST_DISTANCE_BEACON.clear();
            LIST_COORDINATE.clear();
            for (int i = 0; i < LIST.size(); i++) {
                if (LIST.get(i) != 0) {
                    String NAME = LIST_BEACON.get(i);
                    Integer INDEX = beaconInfo.getBEACON_ID().indexOf(NAME);
                    PointF COORDINATE = beaconInfo.getLIST_COORDINATE().get(INDEX);
                    LIST_DISTANCE_BEACON.add(LIST.get(i));
                    LIST_COORDINATE.add(COORDINATE);
                }
            }
            trilaterate(LIST_COORDINATE.get(0), LIST_COORDINATE.get(1), LIST_COORDINATE.get(2),
                    LIST_DISTANCE_BEACON.get(0), LIST_DISTANCE_BEACON.get(1), LIST_DISTANCE_BEACON.get(2));
        }
    }


    private static void trilaterate(PointF a, PointF b, PointF c, float distA, float distB, float distC) {
        float P1[] = {a.x, a.y, 0};
        float P2[] = {b.x, b.y, 0};
        float P3[] = {c.x, c.y, 0};
        float ex[] = {0, 0, 0};
        float P2P1 = 0;
        for (int i = 0; i < 3; i++) {
            P2P1 += Math.pow(P2[i] - P1[i], 2);
        }
        for (int i = 0; i < 3; i++) {
            ex[i] = (float) ((P2[i] - P1[i]) / Math.sqrt(P2P1));
        }
        float p3p1[] = {0, 0, 0};
        for (int i = 0; i < 3; i++) {
            p3p1[i] = P3[i] - P1[i];
        }
        float ivar = 0;
        for (int i = 0; i < 3; i++) {
            ivar += (ex[i] * p3p1[i]);
        }
        float p3p1i = 0;
        for (int i = 0; i < 3; i++) {
            p3p1i += Math.pow(P3[i] - P1[i] - ex[i] * ivar, 2);
        }
        float ey[] = {0, 0, 0};
        for (int i = 0; i < 3; i++) {
            ey[i] = (float) ((P3[i] - P1[i] - ex[i] * ivar) / Math.sqrt(p3p1i));
        }
        float ez[] = {0, 0, 0};
        float d = (float) Math.sqrt(P2P1);
        float jvar = 0;
        for (int i = 0; i < 3; i++) {
            jvar += (ey[i] * p3p1[i]);
        }
        float x = (float) ((Math.pow(distA, 2) - Math.pow(distB, 2) + Math.pow(d, 2)) / (2 * d));
        float y = (float) (((Math.pow(distA, 2) - Math.pow(distC, 2) + Math.pow(ivar, 2)
                + Math.pow(jvar, 2)) / (2 * jvar)) - ((ivar / jvar) * x));
        float z = (float) Math.sqrt(Math.pow(distA, 2) - Math.pow(x, 2) - Math.pow(y, 2));
        if (Float.isNaN(z)) z = 0;
        float triPt[] = {0, 0, 0};
        for (int i = 0; i < 3; i++) {
            triPt[i] = P1[i] + ex[i] * x + ey[i] * y + ez[i] * z;
        }
        float lon = triPt[0];
        float lat = triPt[1];
        Log.d(TAG, " x = " + lon + " y = " + lat);
    }

}
