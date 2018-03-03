package by.grsu.ftf.indoornav.administrator.activityEntry;

import android.arch.lifecycle.ViewModelProviders;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.indoornav.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import by.grsu.ftf.beaconlib.BeaconControllerService;
import by.grsu.ftf.indoornav.administrator.ConnectionService;
import by.grsu.ftf.indoornav.administrator.activitySearch.SearchActivity;
import by.grsu.ftf.indoornav.db.BeaconViewModel;
import by.grsu.ftf.indoornav.db.beaconAdmin.BeaconAdmin;

public class EntryActivity extends AppCompatActivity implements BeaconControllerService.Callbacks {

    private BeaconViewModel mViewModel;
    private TimerView timerView;
    private List<BeaconAdmin> mBeacon;
    private BeaconAdmin beacon;
    private ConnectionService mConnection;
    private Integer time;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        initView();
        if(savedInstanceState == null) timerBeacon();
    }

    private void initView() {
        beacon = (BeaconAdmin) getIntent().getSerializableExtra(SearchActivity.BEACON_INFO);
        timerView = (TimerView) findViewById(R.id.timerView);
        mBeacon = new ArrayList<>();
        time = 5;

        room();
        connectionService();
    }

    private void room() {
        mViewModel = ViewModelProviders.of(this).get(BeaconViewModel.class);
        mViewModel.deleteBeaconAdmin(beacon);
    }

    private void connectionService() {
        mConnection = new ConnectionService();
        mConnection.mConnection(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mViewModel.getTime() != null && mViewModel.getTime() != 1) {
            time = mViewModel.getTime();
            timerBeacon();
        } else {
            if (mViewModel.getAdmin() != null) {
                mBeacon = mViewModel.getAdmin();
                timerView.mFlag(false);
            }
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        mConnection.unBindService();
        mViewModel.setAdmin(mBeacon);
    }

    private void timerBeacon() {
        timerView.setTimerView(time);
        mConnection.bindService();
        new CountDownTimer(time * 1000, 1000) {
            @Override
            public void onTick(long l) {
                mViewModel.setTime((int) (l / 1000));
                timerView.tim((int)(l/1000));
            }

            @Override
            public void onFinish() {
                timerView.mFlag(false);
                time = 5;
                mConnection.unBindService();
                dataBase();
            }
        }.start();
    }

    @Override
    public void updateClient(List<String> list) {
        if (beacon.getName().equals(list.get(0))) {
            beacon = new BeaconAdmin();
            beacon.setId(Integer.parseInt(list.get(0).substring(2)));
            beacon.setName(list.get(0));
            beacon.setRSSI(list.get(1));
            mBeacon.add(beacon);
        }
    }

    private void dataBase() {
        float sum = 0;
        for (BeaconAdmin beacon : mBeacon) {
            sum = sum + Float.valueOf(beacon.getRSSI());
        }
        Float a = sum / mBeacon.size();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Vadim")
                 .child("Beacon" + beacon.getName().substring(2))
                 .child("base")
                 .setValue(a);
    }
}
