package by.grsu.ftf.indoornav.db.classesAssistant;

import android.graphics.PointF;

/**
 * Created by Vadzim on 04.02.2018.
 */

public class DeviceCoordinate {

    private PointF coordinate;
    private Float distance;

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
