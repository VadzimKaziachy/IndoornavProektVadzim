package by.grsu.ftf.indoornav.navigation.map;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.indoornav.R;

import java.util.List;

import by.grsu.ftf.indoornav.Beacon.Beacon;
import by.grsu.ftf.indoornav.Beacon.Repository;
import by.grsu.ftf.indoornav.MainActivity;
import by.grsu.ftf.indoornav.storage.DataBaseFireBase;
import by.grsu.ftf.indoornav.util.Distance;
import by.grsu.ftf.indoornav.util.InternetInquiryFragment;

import static by.grsu.ftf.indoornav.MainActivity.BEACON_MAP;
import static by.grsu.ftf.indoornav.MainActivity.DIALOG_INTERNET;

/**
 * Created by Vadzim on 13.01.2018.
 */

public class MapActivity extends AppCompatActivity {

    private Repository repository;
    private Map map;
    private List<Beacon> beacons;
    private Distance distance;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_map);

        if (!MainActivity.isOnline(this) && savedInstanceState == null) {
            FragmentManager manager = getSupportFragmentManager();
            InternetInquiryFragment interne = new InternetInquiryFragment();
            interne.show(manager, DIALOG_INTERNET);
        }

        beacons = (List<Beacon>) getIntent().getSerializableExtra(BEACON_MAP);
        repository = ViewModelProviders.of(this).get(Repository.class);
        distance = new Distance();
        map = (Map) findViewById(R.id.map);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (MainActivity.isOnline(this)) {
            if (beacons.get(0) != null) {
                DataBaseFireBase dataBase = new DataBaseFireBase();
                List<Beacon> mBeacon = dataBase.dataBaseFireBase(this);
                beacons = distance.mCoordinate(beacons, mBeacon);
            }
            map.provider(beacons);
        }
    }
}
