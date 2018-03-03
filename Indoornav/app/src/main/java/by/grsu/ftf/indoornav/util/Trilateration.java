package by.grsu.ftf.indoornav.util;

import android.graphics.PointF;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import by.grsu.ftf.indoornav.db.classesAssistant.DeviceCoordinate;
import by.grsu.ftf.indoornav.db.beacon.Beacon;

/**
 * Created by Vadzim on 21.01.2018.
 */

public class Trilateration {

    public PointF coordinatesOfThePhone(List<Beacon> mBeacon) {

        List<DeviceCoordinate> maxBeaconList = new ArrayList<>();
        DeviceCoordinate maxBeacon;

        for (Beacon beacon : mBeacon) {
            if (beacon.getX() != null && maxBeaconList.size() < 3) {
                maxBeacon = new DeviceCoordinate();
                maxBeacon.setCoordinate(new PointF(beacon.getX(), beacon.getY()));
                maxBeacon.setDistance(beacon.getDistance());
                maxBeaconList.add(maxBeacon);
            }
        }
        if (maxBeaconList.size() == 3) {
            return trilaterate(maxBeaconList.get(0).getCoordinate(),
                    maxBeaconList.get(1).getCoordinate(),
                    maxBeaconList.get(2).getCoordinate(),
                    maxBeaconList.get(0).getDistance(),
                    maxBeaconList.get(1).getDistance(),
                    maxBeaconList.get(2).getDistance());
        }
        return null;
    }


    private static PointF trilaterate(PointF a, PointF b, PointF c, float distA, float distB, float distC) {
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

        if (Float.isNaN(lon) && Float.isNaN(lat)) return null;

        return new PointF(lon, lat);
    }

    public PointF mDeviceCoordinate(List<Beacon> mBeacon){
        for(Beacon b : mBeacon){
            if(b.getFlagBeacon()){
                return new PointF(b.getX(), b.getY());
            }
        }
        return null;
    }
}
