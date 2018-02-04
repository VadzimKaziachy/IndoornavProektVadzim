package by.grsu.ftf.indoornav.db;

import android.graphics.PointF;

/**
 * Created by Vadzim on 04.02.2018.
 */

public class DeviceCoordinate {
    PointF coordinate;
    Float distance;

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public PointF getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(PointF coordinate) {
        this.coordinate = coordinate;
    }
}
