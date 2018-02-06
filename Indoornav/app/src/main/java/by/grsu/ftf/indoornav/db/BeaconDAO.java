package by.grsu.ftf.indoornav.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Vadzim on 03.02.2018.
 */
@Dao
public interface BeaconDAO {

    @Query("SELECT * FROM beacon")
    LiveData<List<Beacon>> getAll();

    @Query("DELETE  FROM beacon")
    void deleteAll();

    @Update
    void updateList(List<Beacon> beaconList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setBeacon(Beacon beacon);
}
