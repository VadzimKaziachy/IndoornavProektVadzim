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
import android.view.View;
import android.widget.TextView;

import com.example.indoornav.R;

import java.util.ArrayList;
import java.util.List;

import by.grsu.ftf.beaconlib.BeaconControllerService;
import by.grsu.ftf.indoornav.storage.TestBeacon;
import by.grsu.ftf.indoornav.util.Adapter_RecyclerView;
import by.grsu.ftf.indoornav.util.ClickListener;
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
        setOnclickListener();
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

        adapter_recyclerView = new Adapter_RecyclerView(list_beacon, list_distance);
        recyclerView.setAdapter(adapter_recyclerView);
        adapter_recyclerView.notifyDataSetChanged();
    }

    private void setOnclickListener(){
        recyclerView.addOnItemTouchListener(new ClickListener(this) {
            @Override
            public void onItemClick(RecyclerView recyclerView, View itemView, int position) {

                TextView textView = (TextView) itemView.findViewById(R.id.textView);
                String ID = textView.getText().toString();

                Intent intent = new Intent(MainActivity.this, beaconMainActivity.class);
                intent.putExtra("id", ID);
                startActivity(intent);
            }
        });
    }

}




