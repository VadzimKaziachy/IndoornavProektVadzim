package by.grsu.ftf.indoornav.db;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import by.grsu.ftf.indoornav.db.beacon.Beacon;
import by.grsu.ftf.indoornav.db.beacon.BeaconDAO;
import by.grsu.ftf.indoornav.db.beaconAdmin.BeaconAdmin;
import by.grsu.ftf.indoornav.db.beaconAdmin.BeaconAdminDAO;

/**
 * Created by Vadzim on 03.02.2018.
 */

public class BeaconRepository {

    private final BeaconDAO beaconDAO;
    private final BeaconAdminDAO beaconAdminDAO;
    private final Executor executor;

    public BeaconRepository(Context context) {
        beaconDAO = BeaconDatabase.getDatabase(context).beaconDAO();
        beaconAdminDAO = BeaconDatabase.getDatabase(context).beaconAdminDAO();
        executor = Executors.newFixedThreadPool(1);
    }

    public void addBeacon(final Beacon beacon) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                beaconDAO.setBeacon(beacon);
            }
        });
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

    public LiveData<List<Beacon>> getAll() {
        return beaconDAO.getAll();
    }

    public LiveData<List<BeaconAdmin>> getAllAdmin() {
        return beaconAdminDAO.getAll();
    }

    public void addBeaconAdmin(final BeaconAdmin mBeacon) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                beaconAdminDAO.setBeacon(mBeacon);
            }
        });
    }

    public void deleteBeaconAdminAll() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                beaconAdminDAO.deleteBeaconAdminAll();
            }
        });
    }

    public void deleteBeaconAdmin(final BeaconAdmin mBeacon) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                beaconAdminDAO.deleteBeaconAdmin(mBeacon);
            }
        });
    }
}
