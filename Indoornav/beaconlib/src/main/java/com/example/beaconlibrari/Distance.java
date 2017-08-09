package com.example.beaconlibrari;

import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;

/**
 * Created by Вадим on 06.08.2017.
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class Distance {

    private double x, y;
    private String name;
    private int txPower;
    private double dist = 0;
    private ArrayList<Integer> mid = new ArrayList<>();
    int numBeacons = 4;
    Distance[] beacons = (Distance[]) new Distance[numBeacons];
    public int[] used = new int[3];

    public static String Bottom_Left, Bottom_Rights, Top_Left, Top_Rights;
    public static Float Value_X1, Value_Y1;

    private void setBeacon(double x, double y, String name, int txPower) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.txPower = txPower;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setCoord()
    {
        beacons[0] = new Distance();
        beacons[0].setBeacon(0, 0, "id1", -65);

        beacons[1] = new Distance();
        beacons[1].setBeacon(4, 0, "id2", -65);

        beacons[2] = new Distance();
        beacons[2].setBeacon(0, 3, "id3", -65);

        beacons[3] = new Distance();
        beacons[3].setBeacon(4, 3, "id4", -65);
    }

    public ScanCallback leScanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            for (int i = 0; i < numBeacons; i++) {
                if (result.getDevice().getName().equals(beacons[i].getName())) {
                    beacons[i].addMid(result.getRssi());

                    /*TextView text3 = (TextView) findViewById(R.id.textView3);
                    text3.setText(String.valueOf(beacons[0].getDist()));*/
                    Bottom_Left = String.valueOf(beacons[0].getDist());

                    /*TextView text4 = (TextView) findViewById(R.id.textView4);
                    text4.setText(String.valueOf(beacons[1].getDist()));*/
                    Bottom_Rights = String.valueOf(beacons[1].getDist());

                    /*TextView text5 = (TextView) findViewById(R.id.textView5);
                    text5.setText(String.valueOf(beacons[2].getDist()));*/
                    Top_Left = String.valueOf(beacons[2].getDist());

                    if (result.getDevice().getName().equals("id4")) {
                        /*TextView text6 = (TextView) findViewById(R.id.textView6);
                        text6.setText(String.valueOf(result.getRssi()));*/
                        Top_Rights = String.valueOf(result.getRssi());
                    }
                }
            }
        }
    };

   private void addMid(int power)
    {
        int summ = 0;

        if (mid.size() < 6)
        {
            mid.add(power);
        }
        else
        {
            mid.remove(0);
            mid.add(power);
        }

        for (int j = 0 ; j < mid.size(); j++)
        {
            summ += mid.get(j);
        }

        dist = Math.pow(10, ((float) summ / mid.size() - txPower) / ((float) -10 * 3.2));
    }

    double getX()
    {
        return x;
    }

    double getY()
    {
        return y;
    }

    String getName()
    {
        return name;
    }

    int getTxPower()
    {
        return txPower;
    }

    double getDist()
    {
        return dist;
    }

}
//в этом классе будет определяться расстояние до Beacon, сейчас я решил пропустить класс TypeBeacon так как у нас пока есть
//тока один тип Beacon