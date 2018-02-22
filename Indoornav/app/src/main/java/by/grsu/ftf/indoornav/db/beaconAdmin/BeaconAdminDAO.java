package by.grsu.ftf.indoornav.db.beaconAdmin;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import by.grsu.ftf.indoornav.db.Beacon;

/**
 * Created by Vadzim on 17.02.2018.
 */
@Dao
public interface BeaconAdminDAO {

    @Query("SELECT * FROM beaconAdmin")
    LiveData<List<BeaconAdmin>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setBeacon(BeaconAdmin mBeacon);

    @Query("DELETE FROM beaconadmin")
    void deleteBeaconAdminAll();

    @Delete
    void deleteBeaconAdmin(BeaconAdmin mBeacon);
}
