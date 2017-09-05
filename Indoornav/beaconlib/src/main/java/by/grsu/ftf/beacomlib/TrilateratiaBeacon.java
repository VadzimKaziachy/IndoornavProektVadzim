package by.grsu.ftf.beacomlib;

import android.graphics.PointF;

import java.util.ArrayList;

/**
 * Created by Вадим on 25.07.2017.
 * Here will determine the location of the person in the room,
 * the data will come with BeaconControllerService and by three Beacons we will determine the coordinates of a person,
 * after which the value of x and y will be sent to the class BeaconControllerService, and from there to the main class Beacon Broadcast.
 */

class TrilateratiaBeacon {

    private static ArrayList<String> LIST_X_Y = new ArrayList<>();
    private ArrayList<PointF> coordinates = new ArrayList<>();
    private float a = 0;

    private ArrayList<Float> LIST_DISTANCE_BEACON = new ArrayList<>();
    private ArrayList<PointF> LIST_COORDINATE = new ArrayList<>();

    void setList(ArrayList<Float> LIST_DISTANCE) {

        coordinates.add(new PointF(1, 1));
        coordinates.add(new PointF(5, 1));
        coordinates.add(new PointF(1, 4));
        coordinates.add(new PointF(5, 4));

        if (LIST_DISTANCE.size() > 2) {
            cleaningBeacon(LIST_DISTANCE);
        }
    }

    private void cleaningBeacon(ArrayList<Float> LIST_DISTANCE) {
        for (int i = 0; i < LIST_DISTANCE.size() - 1; i++) {
            if (i == 0) {
                a = Math.max(LIST_DISTANCE.get(i), LIST_DISTANCE.get(i + 1));
            } else if (i == 1) {
                a = Math.max(a, LIST_DISTANCE.get(i + 1));
            } else if (i == 2) {
                a = Math.max(a, LIST_DISTANCE.get(i + 1));
            }
        }
        LIST_DISTANCE.set(LIST_DISTANCE.indexOf(a), (float) 0);
        LIST_DISTANCE_BEACON.clear();
        LIST_COORDINATE.clear();
        for (int i = 0; i < 4; i++) {
            if (LIST_DISTANCE.get(i) != 0) {
                LIST_DISTANCE_BEACON.add(LIST_DISTANCE.get(i));
                LIST_COORDINATE.add(coordinates.get(i));
            }
        }
        trilaterate(LIST_COORDINATE.get(0), LIST_COORDINATE.get(1), LIST_COORDINATE.get(2),
                LIST_DISTANCE_BEACON.get(0), LIST_DISTANCE_BEACON.get(1), LIST_DISTANCE_BEACON.get(2));
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
        LIST_X_Y.clear();
        LIST_X_Y.add(String.valueOf(lon));
        LIST_X_Y.add(String.valueOf(lat));
    }

    ArrayList<String> getLIST_X_Y() {
        return LIST_X_Y;
    }


}

