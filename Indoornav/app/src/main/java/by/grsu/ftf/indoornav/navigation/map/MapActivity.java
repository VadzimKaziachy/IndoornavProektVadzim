package by.grsu.ftf.indoornav.navigation.map;

import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.example.indoornav.R;

import java.io.Serializable;
import java.util.List;

import by.grsu.ftf.beaconlib.BeaconControllerService;
import by.grsu.ftf.indoornav.db.Beacon;
import by.grsu.ftf.indoornav.db.BeaconLifecycle;
import by.grsu.ftf.indoornav.db.Repository;
import by.grsu.ftf.indoornav.MainActivity;
import by.grsu.ftf.indoornav.storage.BeaconMerger;
import by.grsu.ftf.indoornav.storage.DataBaseFireBase;
import by.grsu.ftf.indoornav.util.Distance;
import by.grsu.ftf.indoornav.util.InternetInquiryFragment;
import by.grsu.ftf.indoornav.util.Trilateration;

import static by.grsu.ftf.indoornav.MainActivity.BEACON_COORDINATE;
import static by.grsu.ftf.indoornav.MainActivity.BEACON_MAP;
import static by.grsu.ftf.indoornav.MainActivity.DIALOG_INTERNET;

/**
 * Created by Vadzim on 13.01.2018.
 */

public class MapActivity extends AppCompatActivity implements BeaconControllerService.Callbacks {

    private Repository repository;
    private PointF mDeviceCoordinate;
    private Map map;
    private List<Beacon> beacons;
    private Distance distance;
    private Trilateration mCoordinate;
    boolean mBound;
    private boolean mRecord = true;
    private BeaconMerger beaconMerger;
    private Beacon beacon;
    public static final String LIST_BEACON = "LIST_BEACON";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_map);
        getLifecycle().addObserver(new BeaconLifecycle(this));
        initComponent();

        if (!MainActivity.isOnline(this) && savedInstanceState == null) {
            FragmentManager manager = getSupportFragmentManager();
            InternetInquiryFragment interne = new InternetInquiryFragment();
            interne.show(manager, DIALOG_INTERNET);
        }


        if (savedInstanceState == null) {
            if (beacons != null) {
                beaconMerger.putAll(beacons);
            }
        }

        if (repository.getBeacons() != null) {
            beacons = repository.getBeacons();
            if (beacons != null) {
                beaconMerger.putAll(beacons);
            }
            if (repository.getDeviceCoordinate() != null) {
                mDeviceCoordinate = repository.getDeviceCoordinate();
            }
        }
    }

    private void initComponent() {
        beacons = (List<Beacon>) getIntent().getSerializableExtra(BEACON_MAP);
        List<Beacon> mBeaconCoordinate = (List<Beacon>) getIntent().getSerializableExtra(BEACON_COORDINATE);

        repository = ViewModelProviders.of(this).get(Repository.class);
        distance = new Distance();
        beaconMerger = new BeaconMerger();
        mCoordinate = new Trilateration();
        map = (Map) findViewById(R.id.map);

        if (mBeaconCoordinate.size() != 0) {
            repository.setBeaconCoordinate(mBeaconCoordinate);
            coordinateRecord();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();


        if (MainActivity.isOnline(this)) {
            if (repository.getBeaconCoordinate() == null) {
                DataBaseFireBase dataBase = new DataBaseFireBase();
                List<Beacon> mBeacon = dataBase.dataBaseFireBase(this);
                repository.setBeaconCoordinate(mBeacon);
            } else {
                map.provider(beacons, mDeviceCoordinate);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        repository.setBeacons(beacons);
        repository.setDeviceCoordinate(mDeviceCoordinate);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        //replaces the default 'Back' button action
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            Intent intent = new Intent();
            intent.putExtra(LIST_BEACON, (Serializable) beacons);
            setResult(RESULT_OK, intent);
            finish();
        }
        return true;
    }



    @Override
    public void updateClient(List<String> list) {
        coordinateRecord();

        beacon = distance.distanceBeacon(list, repository.getBeaconCoordinate());
        beaconMerger.put(beacon);
        beacons = beaconMerger.getBeacons();
        mDeviceCoordinate = new Trilateration().coordinatesOfThePhone(beaconMerger.putAllBeaconMap(beacons));

        map.provider(beacons, mDeviceCoordinate);

    }

    private void coordinateRecord() {
        if (repository.getBeaconCoordinate() != null && mRecord) {
            List<Beacon> mBeacon = distance.mCoordinate(beacons, repository.getBeaconCoordinate());
            beaconMerger.putAll(mBeacon);
            mDeviceCoordinate = new Trilateration().coordinatesOfThePhone(beaconMerger.putAllBeaconMap(beacons));
            mRecord = false;
        }
    }
}
