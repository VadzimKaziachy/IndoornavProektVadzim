package by.grsu.ftf.indoornav;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.indoornav.R;

import java.util.List;

import by.grsu.ftf.beaconlib.BeaconControllerService;
import by.grsu.ftf.indoornav.administrator.activitySearch.SearchActivity;
import by.grsu.ftf.indoornav.db.BeaconLifecycle;
import by.grsu.ftf.indoornav.db.BeaconViewModel;
import by.grsu.ftf.indoornav.adapter.ClickListener;
import by.grsu.ftf.indoornav.adapter.RecyclerView_Adapter;
import by.grsu.ftf.indoornav.beaconInfo.BeaconFragment;
import by.grsu.ftf.indoornav.beaconInfo.FragmentActivity;
import by.grsu.ftf.indoornav.navigation.map.MapActivity;
import by.grsu.ftf.indoornav.storage.BeaconMerger;
import by.grsu.ftf.indoornav.db.Beacon;
import by.grsu.ftf.indoornav.storage.DataBaseFireBase;
import by.grsu.ftf.indoornav.util.Distance;
import by.grsu.ftf.indoornav.util.InternetInquiryFragment;

/*
 * This class is the main, some kind of kernel program.
 * The program goes to the Bluetooth class at startup, which checks if Bluetooth is turned on.
 */

public class MainActivity extends AppCompatActivity implements BeaconControllerService.Callbacks {

    private RecyclerView recyclerView;
    private RecyclerView_Adapter recyclerView_adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private BeaconMerger beaconMerger = new BeaconMerger();
    private Distance distance = new Distance();

    private BeaconViewModel beaconViewModel;
    private Beacon beacon;
    private List<Beacon> beacons;
    private int positionBeacon;

    public static final String BEACON_FRAGMENT = "BEACON_FRAGMENT";
    public static final String DIALOG_INTERNET = "DIALOG_INTERNET";

    int position = 0;
    Boolean mMerger;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masterdetail);
        getLifecycle().addObserver(new BeaconLifecycle(this));
        initViews();
        adapter();

        if (!isOnline(this) && savedInstanceState == null) {
            FragmentManager manager = getSupportFragmentManager();
            InternetInquiryFragment internet = new InternetInquiryFragment();
            internet.show(manager, DIALOG_INTERNET);
        }

        if (savedInstanceState == null) {
            dataBaseFireBase();
            beaconViewModel.deleteAll();
        }

        beaconViewModel.getBeacon().observe(this, new Observer<List<Beacon>>() {
            @Override
            public void onChanged(@Nullable List<Beacon> mBeacon) {
                beacons = mBeacon;
                gggg();
            }
        });

    }

    private void gggg() {
        if (beacons != null) {
            if (mMerger) {
                beaconMerger.putAll(beacons);
                mMerger = false;
            }
            transmitsBeaconAdapter(beacons, position);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMerger = true;
    }

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        beaconViewModel = ViewModelProviders.of(this).get(BeaconViewModel.class);
    }

    private void adapter() {
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView_adapter = new RecyclerView_Adapter();
        recyclerView.setAdapter(recyclerView_adapter);
        recyclerView_adapter.setOnItemClickListener(new ClickListener() {
            @Override
            public void onItemClick(Beacon beacon, View view) {
                if (findViewById(R.id.detail_fragment_container) == null) {
                    Intent intent = new Intent(MainActivity.this, FragmentActivity.class);
                    intent.putExtra(BEACON_FRAGMENT, beacon);
                    startActivity(intent);
                } else {
                    Fragment beaconFragment = BeaconFragment.newInstance(beacon);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.detail_fragment_container, beaconFragment);
                    ft.commit();

                }
            }
        });
    }


    @Override
    public void updateClient(List<String> list) {
        beacon = distance.distanceBeacon(list, beaconViewModel.getBeaconCoordinate());
        position = beaconMerger.put(beacon);

        beaconViewModel.beaconSort(beacon);
    }


    private void transmitsBeaconAdapter(List<Beacon> beacons, int position) {
        recyclerView_adapter.setBeacon(beacons);
        if (beacons.size() == positionBeacon) {
            recyclerView_adapter.notifyItemChanged(position);
        } else if (beacons.size() > positionBeacon) {
            recyclerView_adapter.notifyDataSetChanged();
        } else if (beacons.size() < positionBeacon) {
            recyclerView_adapter.notifyItemRemoved(position);
        }
        positionBeacon = beacons.size();
    }

    private void dataBaseFireBase() {
        DataBaseFireBase dataBase = new DataBaseFireBase();
        List<Beacon> mBeacon = dataBase.dataBaseFireBase(this);
        beaconViewModel.setBeaconCoordinate(mBeacon);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_map:
                startActivity(new Intent(this, MapActivity.class));
                return true;
            case R.id.menu_administrator:
                startActivity(new Intent(this, SearchActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
}