package by.grsu.ftf.indoornav;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.example.indoornav.R;
import java.util.List;
import by.grsu.ftf.beaconlib.BeaconControllerService;
import by.grsu.ftf.indoornav.Beacon.Repository;
import by.grsu.ftf.indoornav.adapter.ClickListener;
import by.grsu.ftf.indoornav.adapter.DividerDecoration;
import by.grsu.ftf.indoornav.adapter.RecyclerView_Adapter;
import by.grsu.ftf.indoornav.beaconInfo.BeaconFragment;
import by.grsu.ftf.indoornav.beaconInfo.FragmentActivity;
import by.grsu.ftf.indoornav.storage.BeaconMerger;
import by.grsu.ftf.indoornav.Beacon.Beacon;
import by.grsu.ftf.indoornav.util.Distance;


/*
 * This class is the main, some kind of kernel program.
 * The program goes to the Bluetooth class at startup, which checks if Bluetooth is turned on.
 */

public class MainActivity extends AppCompatActivity implements BeaconControllerService.Callbacks {

    private RecyclerView recyclerView;
    RecyclerView_Adapter recyclerView_adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private BeaconMerger beaconMerger = new BeaconMerger();
    private Distance distance = new Distance();

    private Repository repository;
    private Beacon beacon;
    private List<Beacon> beacons;
    public static final String BEACON_FRAGMENT = "BEACON_FRAGMENT";

    boolean mBound;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masterdetail);

        initViews();
        adapter();

        repository = ViewModelProviders.of(this).get(Repository.class);
        if (repository.getBeacons() != null) {
            beacons = repository.getBeacons();
            if (beacons != null) {
                beaconMerger.putAll(beacons);
                transmitsBeaconAdapter(beacons);
            }
        }
    }

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
    }

    private void adapter() {
        recyclerView.addItemDecoration(new DividerDecoration(this));
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
    protected void onStart() {
        super.onStart();
        bindService(new Intent(MainActivity.this, BeaconControllerService.class),
                mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
        repository.setBeacons(beacons);
    }


    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BeaconControllerService.MyBinder binder = (BeaconControllerService.MyBinder) service;
            binder.connectCallbacks(MainActivity.this);
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };

    @Override
    public void updateClient(List<String> list1) {

        beacon = new Beacon(distance.distanceBeacon(list1));
        beaconMerger.put(beacon);
        beacons = beaconMerger.getBeacons();

        transmitsBeaconAdapter(beacons);
    }

    private void transmitsBeaconAdapter(List<Beacon> beacons) {
        recyclerView_adapter.setBeacon(beacons);
        recyclerView_adapter.notifyDataSetChanged();
        recyclerView_adapter.notifyItemChanged(beaconMerger.getPosition());
    }
    //джейсон  json
    //retrafit2

}