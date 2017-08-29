package by.grsu.ftf.beacomlib;

import java.util.ArrayList;

/**
 * Created by egor on 14.7.17.
 */

public class Beacon
{
    private double x, y;
    private String name;

    private double dist = 0;
    private ArrayList<Integer> mid = new ArrayList<>();

    void setBeacon(double a, double b, String c, double d)
    {
        this.x = a;
        this.y = b;
        this.name = c;
        this.dist = d;
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



    double getDist()
    {
        return dist;
    }
}
