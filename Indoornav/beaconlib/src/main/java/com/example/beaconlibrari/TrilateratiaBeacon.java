package com.example.beaconlibrari;

/**
 * Created by Вадим on 25.07.2017.
 *
 * здесь будет определяться местоположение человека в помещение, данные буду приходить из класса FilterKalman
 * после чего по силе сигнала будем определять расстояние до Beacon и по трем Beacon будем определять координаты человека
 * после чего значение x и y будет отправляться в класс TypeBeacon, а оттуда в главный класс MainActivity
 */

import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.os.Build;
import android.support.annotation.RequiresApi;



@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class TrilateratiaBeacon extends Distance {


    public int getClosest() {
        double minim = 100;

        for (int i = 0; i < 3; i++) {
            used[i] = -1;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < numBeacons; j++) {
                if ((beacons[j].getDist() <= minim) && (used[0] != j) && (used[1] != j) && (used[2] != j) && (beacons[j].getDist() != 0)) {
                    used[i] = j;
                    minim = beacons[j].getDist();
                }
            }
            minim = 100;
        }

        for (int i = 0; i < 3; i++) {
            if (used[i] == -1) {
                return 0;
            }
        }

        for (int i = 0; i < 3; i++) {
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
    private ScanCallback leScanCallback = new ScanCallback()
    {
        @Override
        public void onScanResult(int callbackType, ScanResult result)
        {

            for (int i = 0; i < numBeacons; i++)
            {
                if (getClosest() == 1)
                    {
                        double x1 = Math.abs((Math.pow(beacons[used[0]].getDist(), 2) - Math.pow(beacons[used[1]].getDist(), 2) + Math.pow(beacons[used[1]].getX(), 2)) / (2 * beacons[used[1]].getX()));
                        double y1 = Math.abs((Math.pow(beacons[used[0]].getDist(), 2) - Math.pow(beacons[used[2]].getDist(), 2) - Math.pow(x1, 2) + Math.pow(x1 - beacons[used[2]].getX(), 2) + Math.pow(beacons[used[2]].getY(), 2)) / (2 * beacons[used[2]].getY()));

                        /*ImageView person = (ImageView) findViewById(R.id.imageView6);
                        ConstraintLayout.LayoutParams params1 = (ConstraintLayout.LayoutParams) (person.getLayoutParams());
                        params1.horizontalBias = ((float) 0.9 / 4) * (float) x1 + 0.05f;
                        params1.verticalBias = 1 - (((float) 0.88 / 6) * (float) y1 + 0.02f);
                        person.setLayoutParams(params1);*/

                        /*TextView text = (TextView) findViewById(R.id.textView);
                        text.setText(String.valueOf(x1));*/
                        Value_X1 = (float) x1;

                        /*TextView text2 = (TextView) findViewById(R.id.textView2);
                        text2.setText(String.valueOf(y1));*/
                        Value_Y1 = (float) y1;
                    }
                }
            }
      };
}




