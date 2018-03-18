package by.grsu.ftf.indoornav.navigation.map;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.indoornav.R;

import java.util.List;

import by.grsu.ftf.beaconlib.BeaconControllerService;
import by.grsu.ftf.indoornav.MainActivity;
import by.grsu.ftf.indoornav.administrator.activitySearch.SearchActivity;
import by.grsu.ftf.indoornav.db.BeaconLifecycle;
import by.grsu.ftf.indoornav.db.BeaconViewModel;
import by.grsu.ftf.indoornav.db.beacon.Beacon;
import by.grsu.ftf.indoornav.db.classesAssistant.BeaconFireBase;
import by.grsu.ftf.indoornav.navigation.map.fragment_1_map.ListMap;
import by.grsu.ftf.indoornav.navigation.map.fragment_1_map.StartFragment2Map;
import by.grsu.ftf.indoornav.navigation.map.fragment_2_map.CallbackCoordin;
import by.grsu.ftf.indoornav.navigation.map.fragment_2_map.GraphicsMap;
import by.grsu.ftf.indoornav.util.Distance;
import by.grsu.ftf.indoornav.util.InternetInquiryFragment;
import by.grsu.ftf.indoornav.util.Trilateration;

import static by.grsu.ftf.indoornav.MainActivity.DIALOG_INTERNET;

/**
 * Created by Vadzim on 13.01.2018.
 */

public class MapActivity extends AppCompatActivity implements BeaconControllerService.Callbacks,
                                                              StartFragment2Map,
                                                              CallbackCoordin {


    private BeaconViewModel beaconViewModel;
    private Distance distance;
    private Trilateration mCoordinate;
    private Beacon beacon;
    private List<Beacon> beacons;
    private boolean mRecord = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_map);
        getLifecycle().addObserver(new BeaconLifecycle(this));
        initComponent();

        if (savedInstanceState == null) {
            beaconViewModel.deleteAll();
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = ListMap.newInstance();
            fm.beginTransaction()
                    .add(R.id.activity_map, fragment)
                    .commit();
        }

        if (!MainActivity.isOnline(this) && savedInstanceState == null) {
            FragmentManager manager = getSupportFragmentManager();
            InternetInquiryFragment internet = new InternetInquiryFragment();
            internet.show(manager, DIALOG_INTERNET);
        }

    }

    private void initComponent() {
        distance = new Distance();
        mCoordinate = new Trilateration();
        beaconViewModel = ViewModelProviders.of(this).get(BeaconViewModel.class);
        beaconViewModel.getBeacon().observe(this, new Observer<List<Beacon>>() {
            @Override
            public void onChanged(@Nullable List<Beacon> mBeacon) {
                beacons = mBeacon;
            }
        });
    }


    @Override
    public void updateClient(List<String> list) {
        coordinateRecord();
        beacon = distance.distanceBeacon(list, beaconViewModel.getBeaconCoordinate());
        beaconViewModel.addBeacon(beacon);
    }

    private void coordinateRecord() {
        if(beaconViewModel.getBeaconCoordinate()!=null) {
            if (beaconViewModel.getBeaconCoordinate().size() != 0 && mRecord) {
                List<Beacon> mBeacon = distance.mCoordinate(beacons, beaconViewModel.getBeaconCoordinate());
                beaconViewModel.updateList(mBeacon);
                mRecord = false;
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_map_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_administrator:
                startActivity(new Intent(this, SearchActivity.class));
                return true;
            case R.id.menu_list:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void startFragment2() {
        Fragment fragment = GraphicsMap.newInstance();
        FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
        fm.replace(R.id.activity_map, fragment);
        fm.commit();
    }

    @Override
    public void callbackCoordin(List<BeaconFireBase> mBeacon) {
        beaconViewModel.setBeaconCoordinate(mBeacon);
    }
}