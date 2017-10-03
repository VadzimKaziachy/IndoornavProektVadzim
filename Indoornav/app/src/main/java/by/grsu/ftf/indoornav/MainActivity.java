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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.indoornav.R;

import java.util.ArrayList;
import java.util.List;

import by.grsu.ftf.beaconlib.BeaconControllerService;
import by.grsu.ftf.indoornav.storage.Storage;
import by.grsu.ftf.indoornav.storage.TestBeacon;
import by.grsu.ftf.indoornav.util.Distance;


/*
 * This class is the main, some kind of kernel program.
 * The program goes to the Bluetooth class at startup, which checks if Bluetooth is turned on.
 */

public class MainActivity extends AppCompatActivity implements BeaconControllerService.Callbacks {

    private Button start;
    private Button stop;
    private ListView listView;
    static ArrayAdapter<String> mAdapter;
    private static List<String> LIST_BEACON = new ArrayList<>();
    private static List<Float> LIST_DISTANCE = new ArrayList<>();
    private static List<String> LIST = new ArrayList<>();
    Distance distance = new Distance();

    boolean mBound;
    boolean clickButton = true;
    BeaconControllerService myBinder;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setOnClickListeners();
    }

    private void initViews() {
        start = (Button) findViewById(R.id.button);
        stop = (Button) findViewById(R.id.button1);
        listView = (ListView) findViewById(R.id.ListView);
        mAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, LIST);
    }

    private void setOnClickListeners() {
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickButton) {
                    startService(new Intent(MainActivity.this, BeaconControllerService.class));
                    startService(new Intent(MainActivity.this, TestBeacon.class));
                    clickButton = false;
                }

            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBound) {
                    unbindService(mConnection);
                    mBound = false;
                    stopService(new Intent(MainActivity.this, BeaconControllerService.class));
                    stopService(new Intent(MainActivity.this, TestBeacon.class));
                    bindService(new Intent(MainActivity.this, BeaconControllerService.class), mConnection, Context.BIND_AUTO_CREATE);
                    clickButton = true;
                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindService(new Intent(MainActivity.this, BeaconControllerService.class), mConnection, Context.BIND_AUTO_CREATE);
        if (Storage.getRepository(getApplicationContext()) != null) {
            writeInList(Storage.getRepository(getApplicationContext()));
            listView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }

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
    public void updateClient(List<String> list) {
        if (LIST_BEACON.contains(list.get(0))) {
            int index = LIST_BEACON.indexOf(list.get(0));
            LIST_BEACON.set(index, list.get(0));
            LIST_DISTANCE.set(index, Float.valueOf(list.get(1)));
        } else {
            LIST_BEACON.add(list.get(0));
            LIST_DISTANCE.add(Float.valueOf(list.get(1)));
        }
        String list_a = "";
        for (int i = 0; i < LIST_BEACON.size(); i++) {
            list_a += LIST_BEACON.get(i) + " - " + LIST_DISTANCE.get(i) + ",";
        }
        Storage.setRepository(getApplicationContext(), list_a);
        writeInList(list_a);
        listView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private void writeInList(String list){
        String[] massif_beacon = list.split(",");
        LIST.clear();
        for (int i = 0; i < massif_beacon.length; i++) {
            LIST.add(massif_beacon[i]);
        }
    }
}




