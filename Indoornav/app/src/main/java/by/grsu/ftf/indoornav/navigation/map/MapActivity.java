package by.grsu.ftf.indoornav.navigation.map;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;

import com.example.indoornav.R;

import java.io.Serializable;
import java.util.List;

import by.grsu.ftf.beaconlib.BeaconControllerService;
import by.grsu.ftf.indoornav.db.Beacon;
import by.grsu.ftf.indoornav.db.BeaconLifecycle;
import by.grsu.ftf.indoornav.db.BeaconViewModel;
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

    private PointF mDeviceCoordinate;
    private List<Beacon> beacons;

    boolean mBound;
    private boolean mRecord = true;

    private Map map;
    private BeaconViewModel beaconViewModel;
    private Distance distance;
    private Trilateration mCoordinate;
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

    }

    private void initComponent() {


        beaconViewModel = ViewModelProviders.of(this).get(BeaconViewModel.class);
        beaconViewModel.getBeacon().observe(this, new Observer<List<Beacon>>() {
            @Override
            public void onChanged(@Nullable List<Beacon> mBeacon) {
                beacons = mBeacon;
            }
        });
        distance = new Distance();
        beaconMerger = new BeaconMerger();
        mCoordinate = new Trilateration();
        map = (Map) findViewById(R.id.map);

        if (beaconViewModel.getDeviceCoordinate() != null) {
            mDeviceCoordinate = beaconViewModel.getDeviceCoordinate();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (MainActivity.isOnline(this)) {
            if (beaconViewModel.getBeaconCoordinate() == null) {
                DataBaseFireBase dataBase = new DataBaseFireBase();
                List<Beacon> mBeacon = dataBase.dataBaseFireBase(this);
                beaconViewModel.setBeaconCoordinate(mBeacon);
            }
            map.provider(beacons, mDeviceCoordinate);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        beaconViewModel.setDeviceCoordinate(mDeviceCoordinate);
    }


    @Override
    public void updateClient(List<String> list) {
        coordinateRecord();

        beacon = distance.distanceBeacon(list, beaconViewModel.getBeaconCoordinate());
        Boolean flag = beaconMerger.put(beacon);
        beaconViewModel.beaconSort(beacon, flag);
        beacons = beaconMerger.getBeacons();
        mDeviceCoordinate = new Trilateration().coordinatesOfThePhone(beaconMerger.putAllBeaconMap(beacons));

        map.provider(beacons, mDeviceCoordinate);

    }

    private void coordinateRecord() {
        if (beaconViewModel.getBeaconCoordinate() != null && mRecord) {
            List<Beacon> mBeacon = distance.mCoordinate(beacons, beaconViewModel.getBeaconCoordinate());
            beaconMerger.putAll(mBeacon);
            mDeviceCoordinate = new Trilateration().coordinatesOfThePhone(beaconMerger.putAllBeaconMap(beacons));
            mRecord = false;
        }
    }
}
