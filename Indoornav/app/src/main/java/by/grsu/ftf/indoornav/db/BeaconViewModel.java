package by.grsu.ftf.indoornav.db;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import java.util.List;

import by.grsu.ftf.indoornav.db.beacon.Beacon;
import by.grsu.ftf.indoornav.db.beaconAdmin.BeaconAdmin;
import by.grsu.ftf.indoornav.db.classesAssistant.BeaconFireBase;

/**
 * Created by Vadzim on 12.12.2017.
 */

public class BeaconViewModel extends AndroidViewModel {
    private MutableLiveData<List<Beacon>> mBeacon;
    private MutableLiveData<List<BeaconAdmin>> mBeaconAdmin;
    private BeaconRepository repository;

    private List<BeaconFireBase> beaconCoordinate;
    private List<BeaconAdmin> mAdmin;
    private Integer time;
    private Integer angle;
    private List<String> List_zal;

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

    public LiveData<List<BeaconAdmin>> getBeaconAdmin() {
        if (mBeaconAdmin == null) {
            mBeaconAdmin = new MutableLiveData<>();
        }
        return repository.getAllAdmin();
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void addBeacon(Beacon beacon) {
        repository.addBeacon(beacon);
    }

    public void updateList(List<Beacon> beaconList) {
        repository.updateList(beaconList);
    }


    public List<BeaconFireBase> getBeaconCoordinate() {
        return beaconCoordinate;
    }

    public void setBeaconCoordinate(List<BeaconFireBase> beaconCoordinate) {
        this.beaconCoordinate = beaconCoordinate;
    }

    public void addBeaconAdmin(BeaconAdmin mBeaconAdmin) {
        repository.addBeaconAdmin(mBeaconAdmin);
    }

    public void deleteBeaconAdminAll() {
        repository.deleteBeaconAdminAll();
    }

    public void deleteBeaconAdmin(BeaconAdmin mBeacon){
        repository.deleteBeaconAdmin(mBeacon);
    }


    public List<BeaconAdmin> getAdmin() {
        return mAdmin;
    }

    public void setAdmin(List<BeaconAdmin> mAdmin) {
        this.mAdmin = mAdmin;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getAngle() {
        return angle;
    }

    public void setAngle(Integer angle) {
        this.angle = angle;
    }

    public List<String> getList_zal() {
        return List_zal;
    }

    public void setList_zal(List<String> list_zal) {
        List_zal = list_zal;
    }
}
