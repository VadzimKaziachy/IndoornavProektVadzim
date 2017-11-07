package by.grsu.ftf.indoornav;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.indoornav.R;

import java.util.ArrayList;
import java.util.List;

import by.grsu.ftf.beaconlib.BeaconControllerService;
import by.grsu.ftf.indoornav.util.Distance;

/**
 * Created by Вадим on 15.10.2017.
 */

public class beaconMainActivity extends Activity implements BeaconControllerService.Callbacks {

    private TextView beacon;
    private TextView distanceBeacon;
    private TextView RSSI;
    private Distance distance = new Distance();
    BeaconControllerService beaconControllerService;
    private Boolean mBound;
    private String id;
    private List<String> list_Beacon = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beacon_info);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        beacon = (TextView) findViewById(R.id.beacon);
        distanceBeacon = (TextView) findViewById(R.id.distance);
        RSSI = (TextView) findViewById(R.id.RSSI);
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindService(new Intent(beaconMainActivity.this, BeaconControllerService.class), connection,
                Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            unbindService(connection);
            mBound = false;
        }
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BeaconControllerService.MyBinder binder = (BeaconControllerService.MyBinder) service;
            beaconControllerService = binder.getService();
            beaconControllerService.registerClient(beaconMainActivity.this);
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };

    @Override
    public void updateClient(List<String> list) {
        if (id.equals(list.get(0))) {
//            String rssi = "RSSI " + list.get(1);
//            list_Beacon.clear();
//            list_Beacon = distance.distanceBeacon(list);
//            beacon.setText(list_Beacon.get(0));
//            distanceBeacon.setText(list_Beacon.get(1));
//            RSSI.setText(rssi);
        }
    }
}
