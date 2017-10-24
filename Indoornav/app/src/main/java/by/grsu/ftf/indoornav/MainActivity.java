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
import android.view.MotionEvent;
import android.view.View;

import com.example.indoornav.R;

import java.util.ArrayList;
import java.util.List;

import by.grsu.ftf.beaconlib.BeaconControllerService;
import by.grsu.ftf.indoornav.adapter.Adapter_RecyclerView;
import by.grsu.ftf.indoornav.adapter.ClickListener;
import by.grsu.ftf.indoornav.storage.TestBeacon;
import by.grsu.ftf.indoornav.util.Distance;


/*
 * This class is the main, some kind of kernel program.
 * The program goes to the Bluetooth class at startup, which checks if Bluetooth is turned on.
 */

public class MainActivity extends AppCompatActivity implements BeaconControllerService.Callbacks {

    private RecyclerView recyclerView;
    Adapter_RecyclerView adapter_recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<String> list = new ArrayList<>();
    private List<String> list_beacon = new ArrayList<>();
    private List<String> list_distance = new ArrayList<>();
    private List<String> list_rssi = new ArrayList<>();

    private Distance distance = new Distance();
    private TestBeacon testBeacon = new TestBeacon();

    boolean mBound;
    BeaconControllerService myBinder;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindService(new Intent(MainActivity.this, BeaconControllerService.class), mConnection,
                Context.BIND_AUTO_CREATE);
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
        list.clear();

        list = distance.distanceBeacon(list1);
        testBeacon.sortingBeacon(list);
        list_beacon = TestBeacon.LIST_BEACON;
        list_distance = TestBeacon.LIST_DISTANCE;
        list_rssi = TestBeacon.LIST_RSSI;

        adapter_recyclerView = new Adapter_RecyclerView(list_beacon, list_distance, list_rssi, new ClickListener<String>() {
            @Override
            public void onClick(View view, String s) {
                view.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()){
                            case MotionEvent.ACTION_DOWN:
                                if (mBound) {
                                    unbindService(mConnection);
                                    mBound = false;
                                }
                                break;
                            case MotionEvent.ACTION_UP:
                                bindService(new Intent(MainActivity.this, BeaconControllerService.class), mConnection,
                                        Context.BIND_AUTO_CREATE);
                                break;
                        }
                        return true;
                    }
                });
            }
        });
        recyclerView.setAdapter(adapter_recyclerView);
        adapter_recyclerView.notifyDataSetChanged();
    }
}




