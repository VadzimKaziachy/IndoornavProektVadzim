package by.grsu.ftf.indoornav.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.graphics.PointF;

import java.io.Serializable;
import java.util.List;


/**
 * Created by Vadzim on 13.11.2017.
 */
@Entity(tableName = "Beacon")
public class Beacon implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    private String name;
    private String RSSI;
    private Float distance;
    private Float progressRSSI;
    private Float RSSIprogress;
    private Float x;
    private Float y;

    public Float getRSSIprogress() {
        return RSSIprogress;
    }

    public Float getProgressRSSI() {
        return progressRSSI;
    }

    public Integer getId() {
        return id;
    }

    public String getRSSI() {
        return RSSI;
    }

    public Float getDistance() {
        return distance;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public void setRSSI(String RSSI) {
        this.RSSI = RSSI;
    }

    public void setProgressRSSI(Float progressRSSI) {
        this.progressRSSI = progressRSSI;
    }

    public void setRSSIprogress(Float RSSIprogress) {
        this.RSSIprogress = RSSIprogress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


