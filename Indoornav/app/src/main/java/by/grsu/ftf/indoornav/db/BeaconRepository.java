package by.grsu.ftf.indoornav.db;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Vadzim on 03.02.2018.
 */

public class BeaconRepository {

    private final BeaconDAO beaconDAO;
    private final Executor executor;

    public BeaconRepository(Context context) {
        beaconDAO = BeaconDatabase.getDatabase(context).beaconDAO();
        executor = Executors.newFixedThreadPool(2);
    }

    public void addBeacon(final Beacon beacon) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                beaconDAO.setBeacon(beacon);
            }
        });
    }

    public void updateBeacon(final Beacon beacon) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                beaconDAO.updateBeacon(beacon);
            }
        });
    }

    public LiveData<List<Beacon>> getAll() {
        return beaconDAO.getAll();
    }

    public void deleteAll() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                beaconDAO.deleteAll();
            }
        });
    }

    public void updateList(final List<Beacon> beaconList) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                beaconDAO.updateList(beaconList);
            }
        });
    }
}
