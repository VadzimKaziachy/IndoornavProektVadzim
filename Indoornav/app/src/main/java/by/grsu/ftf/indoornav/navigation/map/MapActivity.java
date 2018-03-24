package by.grsu.ftf.indoornav.navigation.map;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.indoornav.R;

import java.util.ArrayList;
import java.util.List;

import by.grsu.ftf.beaconlib.BeaconControllerService;
import by.grsu.ftf.indoornav.MainActivity;
import by.grsu.ftf.indoornav.administrator.activitySearch.SearchActivity;
import by.grsu.ftf.indoornav.db.BeaconLifecycle;
import by.grsu.ftf.indoornav.db.BeaconViewModel;
import by.grsu.ftf.indoornav.db.beacon.Beacon;
import by.grsu.ftf.indoornav.db.classesAssistant.BeaconFireBase;
import by.grsu.ftf.indoornav.navigation.map.fragment_0_map.InitialWindow;
import by.grsu.ftf.indoornav.navigation.map.fragment_1_map.DataBaseFireBaseFragmentMap;
import by.grsu.ftf.indoornav.navigation.map.fragment_1_map.ListMap;
import by.grsu.ftf.indoornav.navigation.map.fragment_1_map.StartFragment2Map;
import by.grsu.ftf.indoornav.navigation.map.fragment_2_map.CallbackCoordin;
import by.grsu.ftf.indoornav.navigation.map.fragment_2_map.GraphicsMap;
import by.grsu.ftf.indoornav.util.Distance;
import by.grsu.ftf.indoornav.util.InternetInquiryFragment;
import by.grsu.ftf.indoornav.util.Trilateration;

import static by.grsu.ftf.indoornav.MainActivity.DIALOG_INTERNET;

/**
 * Created by Vadzim on 13.01.2018.
 */

public class MapActivity extends AppCompatActivity implements BeaconControllerService.Callbacks,
        StartFragment2Map,
        CallbackCoordin,
        DataBaseFireBaseFragmentMap.Callback {


    private BeaconViewModel mViewModel;
    private MyCountDownTime myCountDownTimer;
    private Distance distance;
    private Trilateration mCoordinate;
    private Beacon beacon;
    private TimerRun run;
    private Handler handler;
    private List<Beacon> beacons;
    private List<String> list_zal;
    private int time = 0;
    private boolean mRecord = true;
    private boolean mFlagTime = false;
    private boolean mFlagList = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_map);
        getLifecycle().addObserver(new BeaconLifecycle(this));
        initComponent();

        if (savedInstanceState == null) {
            mViewModel.deleteAll();

            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = InitialWindow.newInstance();
            fm.beginTransaction()
                    .add(R.id.activity_map, fragment)
                    .commit();
            time = 3;
            myCountDownTimer = new MyCountDownTime(time * 1000, 1000);
            myCountDownTimer.start();
        }

        if (!isOnline(this) && savedInstanceState == null) {
            FragmentManager manager = getSupportFragmentManager();
            InternetInquiryFragment internet = new InternetInquiryFragment();
            internet.show(manager, DIALOG_INTERNET);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mViewModel.getTime() != null) {
            time = mViewModel.getTime();
            myCountDownTimer = new MyCountDownTime(time * 1000, 1000);
            myCountDownTimer.start();
        }

        if (isOnline(this) && mViewModel.getList_zal() == null) {
            DataBaseFireBaseFragmentMap dataBase = new DataBaseFireBaseFragmentMap(this);
            dataBase.dataBaseFireBase(this);
        } else if (mViewModel.getList_zal() != null) {
            list_zal = mViewModel.getList_zal();
        }
        run.run();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (myCountDownTimer != null) {
            myCountDownTimer.cancel();
        }
        handler.removeCallbacks(run);
    }

    private void initComponent() {
        distance = new Distance();
        mCoordinate = new Trilateration();
        handler = new Handler();
        run = new TimerRun();
        mViewModel = ViewModelProviders.of(this).get(BeaconViewModel.class);
        mViewModel.getBeacon().observe(this, new Observer<List<Beacon>>() {
            @Override
            public void onChanged(@Nullable List<Beacon> mBeacon) {
                beacons = mBeacon;
            }
        });
    }


    @Override
    public void updateClient(List<String> list) {
        coordinateRecord();
        beacon = distance.distanceBeacon(list, mViewModel.getBeaconCoordinate());
        mViewModel.addBeacon(beacon);
    }

    private void coordinateRecord() {
        if (mViewModel.getBeaconCoordinate() != null) {
            if (mViewModel.getBeaconCoordinate().size() != 0 && mRecord) {
                List<Beacon> mBeacon = distance.mCoordinate(beacons, mViewModel.getBeaconCoordinate());
                mViewModel.updateList(mBeacon);
                mRecord = false;
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_map_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_administrator:
                startActivity(new Intent(this, SearchActivity.class));
                return true;
            case R.id.menu_list:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void startFragment2() {
        Fragment fragment = GraphicsMap.newInstance();
        FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
        fm.replace(R.id.activity_map, fragment);
        fm.commit();
    }

    @Override
    public void callbackCoordin(List<BeaconFireBase> mBeacon) {
        mViewModel.setBeaconCoordinate(mBeacon);
    }

    @Override
    public void mCallback(List<String> mList_Zal) {
        mViewModel.setList_zal(mList_Zal);
        list_zal = mList_Zal;
        mFlagList = true;
    }


    private class MyCountDownTime extends CountDownTimer {

        public MyCountDownTime(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            mViewModel.setTime((int) (l / 1000));
        }

        @Override
        public void onFinish() {
            mFlagTime = true;
        }
    }

    private class TimerRun implements Runnable {

        @Override
        public void run() {
            if (mFlagList && mFlagTime) {
                Bundle arg = new Bundle();
                ListMap listMap = new ListMap();
                arg.putStringArrayList("List_zal", (ArrayList<String>) list_zal);
                listMap.setArguments(arg);
                FragmentManager fm = getSupportFragmentManager();
                Fragment fragment = listMap;
                fm.beginTransaction()
                        .replace(R.id.activity_map, fragment)
                        .commit();
                mFlagTime = false;
                mFlagList = false;
            }
            handler.postDelayed(run, 500);
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