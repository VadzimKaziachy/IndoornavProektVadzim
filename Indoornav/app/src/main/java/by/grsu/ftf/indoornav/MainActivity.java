package by.grsu.ftf.indoornav;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.indoornav.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import by.grsu.ftf.beaconlib.BeaconControllerService;
import by.grsu.ftf.indoornav.Beacon.Repository;
import by.grsu.ftf.indoornav.adapter.ClickListener;
import by.grsu.ftf.indoornav.adapter.RecyclerView_Adapter;
import by.grsu.ftf.indoornav.beaconInfo.BeaconFragment;
import by.grsu.ftf.indoornav.beaconInfo.FragmentActivity;
import by.grsu.ftf.indoornav.navigation.map.Map;
import by.grsu.ftf.indoornav.navigation.map.MapActivity;
import by.grsu.ftf.indoornav.storage.BeaconMerger;
import by.grsu.ftf.indoornav.Beacon.Beacon;
import by.grsu.ftf.indoornav.storage.DataBaseFireBase;
import by.grsu.ftf.indoornav.util.Distance;
import by.grsu.ftf.indoornav.util.InternetInquiryFragment;

//import static by.grsu.ftf.indoornav.navigation.map.MapActivity.LIST_BEACON;


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
    private int positionBeacon;
    public static final String BEACON_FRAGMENT = "BEACON_FRAGMENT";
    public static final String BEACON_MAP = "BEACON_MAP";
    public static final String BEACON_COORDINATE = "BEACON_COORDINATE";
    public static final String DIALOG_INTERNET = "DIALOG_INTERNET";

    boolean mBound;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masterdetail);
        initViews();
        adapter();

        if (!isOnline(this) && savedInstanceState == null) {
            FragmentManager manager = getSupportFragmentManager();
            InternetInquiryFragment internet = new InternetInquiryFragment();
            internet.show(manager, DIALOG_INTERNET);
        }

        repository = ViewModelProviders.of(this).get(Repository.class);
        if (repository.getBeacons() != null) {
            beacons = repository.getBeacons();
            if (beacons != null) {
                beaconMerger.putAll(beacons);
                transmitsBeaconAdapter(beacons, 0);
            }
        }
        if (savedInstanceState == null) {
            dataBaseFireBase();
        }

    }

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
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
    protected void onStart() {
        super.onStart();
        bindService(new Intent(MainActivity.this, BeaconControllerService.class),
                mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unBindService();
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
    public void updateClient(List<String> list) {
        beacon = distance.distanceBeacon(list, repository.getBeaconCoordinate());
        beaconMerger.put(beacon);
        beacons = beaconMerger.getBeacons();
        int position = beaconMerger.getPosition();

        transmitsBeaconAdapter(beacons, position);
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
        repository.setBeaconCoordinate(mBeacon);
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
            case R.id.meny_map:
                unBindService();

                Intent intent = new Intent(this, MapActivity.class);
                intent.putExtra(BEACON_MAP, (Serializable) beacons);
                intent.putExtra(BEACON_COORDINATE, (Serializable) repository.getBeaconCoordinate());
                startActivity(intent);
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

    private void unBindService() {
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        super.onActivityResult(requestCode, resultCode, intent);
//        if(resultCode == RESULT_OK){
//            beacons = (List<Beacon>) intent.getSerializableExtra(LIST_BEACON);
//        }
//    }
}