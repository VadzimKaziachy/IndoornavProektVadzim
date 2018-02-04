package by.grsu.ftf.indoornav.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Vadzim on 03.02.2018.
 */
@Database(entities = {Beacon.class}, version = 1)
public abstract class BeaconDatabase extends RoomDatabase {
    public abstract BeaconDAO beaconDAO();

    private static BeaconDatabase INSTANCE;
    private static final Object LOCK = new Object();

    public synchronized static BeaconDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                if (INSTANCE == null) {
                    INSTANCE =
                            Room.databaseBuilder(context, BeaconDatabase.class, "beacon_db")
                                    .build();
                }
            }

        }
        return INSTANCE;
    }
}
