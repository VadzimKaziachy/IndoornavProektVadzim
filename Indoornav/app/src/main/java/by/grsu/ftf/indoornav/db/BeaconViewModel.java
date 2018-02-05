package by.grsu.ftf.indoornav.db;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import by.grsu.ftf.indoornav.storage.BeaconMerger;

/**
 * Created by Vadzim on 12.12.2017.
 */

public class BeaconViewModel extends AndroidViewModel {
    private MutableLiveData<List<Beacon>> mBeacon;
    private BeaconRepository repository;

    private List<Beacon> beaconCoordinate;

    public BeaconViewModel(@NonNull Application application) {
        super(application);
        repository = new BeaconRepository(this.getApplication());
    }

    public LiveData<List<Beacon>> getBeacon() {
        if (mBeacon == null) {
            mBeacon = new MutableLiveData<>();
        }
        return repository.getAll();
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void beaconSort(Beacon beacon, boolean flag) {
        if (flag) {
            repository.updateBeacon(beacon);
        } else {
            repository.addBeacon(beacon);
        }
    }

    public void updateList(List<Beacon> beaconList){
        repository.updateList(beaconList);
    }


    public List<Beacon> getBeaconCoordinate() {
        return beaconCoordinate;
    }

    public void setBeaconCoordinate(List<Beacon> beaconCoordinate) {
        this.beaconCoordinate = beaconCoordinate;
    }
}
