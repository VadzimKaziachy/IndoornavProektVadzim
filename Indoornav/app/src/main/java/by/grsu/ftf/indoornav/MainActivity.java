package by.grsu.ftf.indoornav;

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

import com.example.indoornav.R;

import java.util.List;

import by.grsu.ftf.beaconlib.BeaconControllerService;
import by.grsu.ftf.indoornav.adapter.DividerDecoration;
import by.grsu.ftf.indoornav.adapter.RecyclerView_Adapter;
import by.grsu.ftf.indoornav.storage.TestBeacon;
import by.grsu.ftf.indoornav.util.Beacon;


/*
 * This class is the main, some kind of kernel program.
 * The program goes to the Bluetooth class at startup, which checks if Bluetooth is turned on.
 */

public class MainActivity extends AppCompatActivity implements BeaconControllerService.Callbacks {

    private RecyclerView recyclerView;
    RecyclerView_Adapter recyclerView_adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private TestBeacon testBeacon = new TestBeacon();
    private Beacon beacon;

    boolean mBound;
    BeaconControllerService myBinder;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        adapter();
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
    }

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BeaconControllerService.MyBinder binder = (BeaconControllerService.MyBinder) service;
            myBinder = binder.getService();
            myBinder.registerClient(MainActivity.this);
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };

    @Override
    public void updateClient(List<String> list1) {

        beacon = new Beacon(list1.get(0), list1.get(1));
        testBeacon.sortingBeacon(beacon);

        recyclerView_adapter.setBeacon(testBeacon.getBeacon());
        recyclerView_adapter.notifyDataSetChanged();

    }

}


//tols:text:='ghthgr';
//bagraynd
//arraymap
//sevInstenc, посмотреть  OnsavedInstanceState