package by.grsu.ftf.indoornav.navigation.map.fragment_2_map;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.indoornav.R;

import java.util.List;

import by.grsu.ftf.indoornav.MainActivity;
import by.grsu.ftf.indoornav.db.BeaconViewModel;
import by.grsu.ftf.indoornav.db.beacon.Beacon;
import by.grsu.ftf.indoornav.db.classesAssistant.BeaconFireBase;
import by.grsu.ftf.indoornav.storage.BeaconMerger;
import by.grsu.ftf.indoornav.storage.DataBaseFireBase;
import by.grsu.ftf.indoornav.util.Distance;
import by.grsu.ftf.indoornav.util.Trilateration;

/**
 * Created by Vadzim on 17.03.2018.
 */

public class GraphicsMap extends Fragment implements DataBaseFireBase.Callback {

    private Map map;
    private BeaconViewModel beaconViewModel;
    private PointF mDeviceCoordinate;
    private PointF mNewDeviceCoordinate;
    private boolean mFlagCor = true;
    private BeaconMerger beaconMerger;
    private Distance distance;
    private List<Beacon> mBeacons;
    private CallbackCoordin mCallbackCoordin;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2_map, container, false);
        map = (Map) view.findViewById(R.id.map);
        room();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponent();
    }

    private void initComponent() {
        beaconMerger = new BeaconMerger();
        distance = new Distance();
    }

    private void room() {
        beaconViewModel = ViewModelProviders.of(this).get(BeaconViewModel.class);
        beaconViewModel.getBeacon().observe(this, new Observer<List<Beacon>>() {
            @Override
            public void onChanged(@Nullable List<Beacon> beacons) {
                mBeacons = beacons;

                mNewDeviceCoordinate = new Trilateration()
                        .mDeviceCoordinate(beaconMerger.putAllBeaconMap(mBeacons));


                if (mFlagCor && mNewDeviceCoordinate != null) {
                    mDeviceCoordinate = mNewDeviceCoordinate;
                    mFlagCor = false;
                }

                if (mDeviceCoordinate == mNewDeviceCoordinate) {
                    map.provider(mBeacons, mNewDeviceCoordinate);
                } else if (mNewDeviceCoordinate == null && mDeviceCoordinate != null) {
                    map.provider(mBeacons, null);
                    mDeviceCoordinate = null;
                } else if (mDeviceCoordinate == null && mNewDeviceCoordinate != null) {
                    map.provider(mBeacons, mNewDeviceCoordinate);
                    mDeviceCoordinate = mNewDeviceCoordinate;
                } else {
                    map.coordinateDevice(mBeacons, mDeviceCoordinate, mNewDeviceCoordinate);
                    mDeviceCoordinate = mNewDeviceCoordinate;
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        if (MainActivity.isOnline(getContext())) {
            if (beaconViewModel.getBeaconCoordinate() == null) {
                DataBaseFireBase dataBase = new DataBaseFireBase(this);
                dataBase.dataBaseFireBase(getContext());
            }
        }
    }

    @Override
    public void mCallingBack(List<BeaconFireBase> mBeacon) {
        mCallbackCoordin.callbackCoordin(mBeacon);
    }

    public static GraphicsMap newInstance() {
        Bundle arg = new Bundle();
        GraphicsMap fragment = new GraphicsMap();
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallbackCoordin = (CallbackCoordin) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement StartFragment2");
        }
    }
}
