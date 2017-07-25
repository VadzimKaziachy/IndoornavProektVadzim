package com.example.indoornavigation;

import android.graphics.PointF;

public class Triang {

    public Triang(){

    }

    public PointF trilaterate(PointF a, PointF b, PointF c, float distA, float distB, float distC) {
        float P1[] = { a.x, a.y, 0 };
        float P2[] = { b.x, b.y, 0 };
        float P3[] = { c.x, c.y, 0 };
        float ex[] = { 0, 0, 0 };
        float P2P1 = 0;
        for (int i = 0; i < 3; i++) {
            P2P1 += Math.pow(P2[i] - P1[i], 2);
        }
        for (int i = 0; i < 3; i++) {
            ex[i] = (float) ((P2[i] - P1[i]) / Math.sqrt(P2P1));
        }
        float p3p1[] = { 0, 0, 0 };
        for (int i = 0; i < 3; i++) {
            p3p1[i] = P3[i] - P1[i];
        }
        float ivar = 0;
        for (int i = 0; i < 3; i++) {
            ivar += (ex[i] * p3p1[i]);
        }
        float p3p1i = 0;
        for (int  i = 0; i < 3; i++) {
            p3p1i += Math.pow(P3[i] - P1[i] - ex[i] * ivar, 2);
        }
        float ey[] = { 0, 0, 0};
        for (int i = 0; i < 3; i++) {
            ey[i] = (float) ((P3[i] - P1[i] - ex[i] * ivar) / Math.sqrt(p3p1i));
        }
        float ez[] = { 0, 0, 0 };
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
        float triPt[] = { 0, 0, 0 };
        for (int i = 0; i < 3; i++) {
            triPt[i] =  P1[i] + ex[i] * x + ey[i] * y + ez[i] * z;
        }
        float lon = triPt[0];
        float lat = triPt[1];
        return new PointF(lon, lat);
    }
    public float dlina(int rssi_1,int rssi_d,float n){
        double a;
        double b;
        double c;
        a=(double)rssi_1;
        b=(double)rssi_d;
        c=Math.pow(10,(a-b)/(10*n));
        return (float)c;
    }
    public float get_distance(int rssi, int tx_power) {
        float accuracy=0.0f;
        if (rssi == 0) {
            return -1.0f; // if we cannot determine accuracy, return -1.
        }

        float ratio = rssi*1.0f/tx_power;
        if (ratio < 1.0) {
            accuracy =  (float)Math.pow(ratio,10.0f);
        }
        else {
            accuracy =  (float)((0.89976f)*Math.pow(ratio,7.7095f) + 0.111f);
        }
        return accuracy;
    }
}
