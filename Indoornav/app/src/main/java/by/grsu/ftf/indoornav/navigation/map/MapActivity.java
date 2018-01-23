package by.grsu.ftf.indoornav.navigation.map;

import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.indoornav.R;

import java.io.Serializable;
import java.util.List;

import by.grsu.ftf.beaconlib.BeaconControllerService;
import by.grsu.ftf.indoornav.Beacon.Beacon;
import by.grsu.ftf.indoornav.Beacon.Repository;
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
//    public static final String LIST_BEACON = "LIST_BEACON";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_map);
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

        bindService(new Intent(MapActivity.this, BeaconControllerService.class),
                mConnection, Context.BIND_AUTO_CREATE);

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
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
        repository.setBeacons(beacons);
        repository.setDeviceCoordinate(mDeviceCoordinate);

//        Intent intent = new Intent();
//        intent.putExtra(LIST_BEACON, (Serializable) beacons);
//        setResult(RESULT_OK, intent);
//        finish();
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BeaconControllerService.MyBinder binder = (BeaconControllerService.MyBinder) service;
            binder.connectCallbacks(MapActivity.this);
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };


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
            mRecord = false;
        }
    }
}
