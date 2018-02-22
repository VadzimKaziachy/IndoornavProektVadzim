package by.grsu.ftf.indoornav.db.beaconAdmin;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * Created by Vadzim on 17.02.2018.
 */
@Entity
public class BeaconAdmin implements Serializable {

    @PrimaryKey
    private Integer id;
    private String name;
    private String RSSI;


    public String getRSSI() {
        return RSSI;
    }

    public void setRSSI(String RSSI) {
        this.RSSI = RSSI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
