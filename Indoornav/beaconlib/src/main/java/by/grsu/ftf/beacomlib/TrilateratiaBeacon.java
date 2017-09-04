package by.grsu.ftf.beacomlib;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Вадим on 25.07.2017.
 * Here will determine the location of the person in the room,
 * the data will come with BeaconControllerService and by three Beacons we will determine the coordinates of a person,
 * after which the value of x and y will be sent to the class BeaconControllerService, and from there to the main class Beacon Broadcast.
 */

class TrilateratiaBeacon {

    private ArrayList<Double> LIST_DISTANCE = new ArrayList<>();
    private ArrayList<String> LIST_X_Y = new ArrayList<>();
    private double a;
    private double x1 = 0;
    private double y1 = 0;

    private int numBeacons = 4;
    private Beacon[] beacons = (Beacon[]) new Beacon[numBeacons];
    private int[] used = new int[3];


    void setList(ArrayList<Double> LIST_DISTANCE) {
        if (this.LIST_DISTANCE.size() > 4) {
            this.LIST_DISTANCE.clear();
            this.LIST_DISTANCE = LIST_DISTANCE;
        } else {
            this.LIST_DISTANCE = LIST_DISTANCE;
        }


        if (this.LIST_DISTANCE.size() == 4) {
            // cleaningBeacon(this.LIST_DISTANCE);
            setCoord(this.LIST_DISTANCE.get(0), this.LIST_DISTANCE.get(1), this.LIST_DISTANCE.get(2), this.LIST_DISTANCE.get(3));
            getClosest();
            trilateratia();
        }

    }


    private void cleaningBeacon(ArrayList<Double> list) {

        for (int i = 0; i < list.size() - 1; i++) {
            if (i == 0) {
                a = Math.min(list.get(i), list.get(i + 1));
            } else if (i == 1) {
                a = Math.min(a, list.get(i + 1));
            } else if (i == 2) {
                a = Math.min(a, list.get(i + 1));
            }
        }
        list.set(list.indexOf(a), (double) 0);
    }


    private void setCoord(double beacon1, double beacon2, double beacon3, double beacon4) {

        beacons[0] = new Beacon();
        beacons[0].setBeacon(0, 0, "id1", beacon1);

        beacons[1] = new Beacon();
        beacons[1].setBeacon(4, 0, "id2", beacon2);

        beacons[2] = new Beacon();
        beacons[2].setBeacon(0, 3, "id3", beacon3);

        beacons[3] = new Beacon();
        beacons[3].setBeacon(4, 3, "id4", beacon4);
    }

    private int getClosest() {
        int mandatoryBeacons = 3;
        double minim = 100;

        for (int i = 0; i < mandatoryBeacons; i++) {
            used[i] = -1;
        }

        for (int i = 0; i < mandatoryBeacons; i++) {
            for (int j = 0; j < numBeacons; j++) {
                if ((beacons[j].getDist() <= minim) && (used[0] != j) && (used[1] != j) && (used[2] != j) && (beacons[j].getDist() != 0)) {
                    used[i] = j;
                    minim = beacons[j].getDist();
                }
            }
            minim = 100;
        }

        for (int i = 0; i < mandatoryBeacons; i++) {
            if (used[i] == -1) {
                return 0;
            }
        }

        for (int i = 0; i < mandatoryBeacons; i++) {
            if (beacons[used[i]].getX() == 0 && beacons[used[i]].getY() == 0) {
                int x = used[0];
                used[0] = used[i];
                used[i] = x;
            } else if (beacons[used[i]].getY() == 0) {
                int x = used[1];
                used[1] = used[i];
                used[i] = x;
            } else if (beacons[used[i]].getX() == 0) {
                int x = used[2];
                used[2] = used[i];
                used[i] = x;
            }
        }
        return 1;
    }


    private void trilateratia() {

        if (getClosest() == 1) {
            x1 = Math.abs((Math.pow(beacons[used[0]].getDist(), 2) - Math.pow(beacons[used[1]].getDist(), 2) + Math.pow(beacons[used[1]].getX(), 2)) / (2 * beacons[used[1]].getX()));
            y1 = Math.abs((Math.pow(beacons[used[0]].getDist(), 2) - Math.pow(beacons[used[2]].getDist(), 2) - Math.pow(x1, 2) + Math.pow(x1 - beacons[used[2]].getX(), 2) + Math.pow(beacons[used[2]].getY(), 2)) / (2 * beacons[used[2]].getY()));
            Log.d("Log", "x - " + String.valueOf(x1) + " y - " + String.valueOf(y1));
        }
        if (x1 != 0 && y1 != 0 && x1 < 1000) {
            LIST_X_Y.clear();
            LIST_X_Y.add(String.valueOf(x1));
            LIST_X_Y.add(String.valueOf(y1));
        }
    }

    ArrayList<String> getLIST_X_Y() {
        return LIST_X_Y;
    }


}

