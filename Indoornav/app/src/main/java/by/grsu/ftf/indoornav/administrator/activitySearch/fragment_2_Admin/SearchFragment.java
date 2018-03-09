package by.grsu.ftf.indoornav.administrator.activitySearch.fragment_2_Admin;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.indoornav.R;

import by.grsu.ftf.indoornav.administrator.ConnectionService;
import by.grsu.ftf.indoornav.administrator.activitySearch.fragment_1_Admin.StartFragment2;
import by.grsu.ftf.indoornav.administrator.activitySearch.fragment_3_admin.ListFragment;
import by.grsu.ftf.indoornav.db.BeaconViewModel;

/**
 * Created by Vadzim on 05.03.2018.
 */

public class SearchFragment extends Fragment {


    private BeaconViewModel mViewModel;
    private ConnectionService mConnect;
    private Integer time;
    private TimerView timerView;
    private StartFragment3 startFragment;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2_admin, container, false);
        timerView = (TimerView) view.findViewById(R.id.timerView);
        time = 5;
        if (savedInstanceState == null) timerBeacon();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setRetainInstance(true);
        room();
        connectionService();
    }

    private void room() {
//        mViewModel = ViewModelProviders.of(this).get(BeaconViewModel.class);
    }


    private void connectionService() {
        mConnect = new ConnectionService();
        mConnect.mConnection(getActivity());
    }

    @Override
    public void onStart() {
        super.onStart();
//        if (mViewModel.getTime() != null && mViewModel.getTime() != 1) {
//            time = mViewModel.getTime();
            timerBeacon();
//        }
    }

    @Override
    public void onStop() {
        super.onStop();
        mConnect.unBindService();
    }

    private void timerBeacon() {
        mConnect.bindService();
//        timerView.setTimerView(time);
        new CountDownTimer(5 * 1000, 1000) {

            @Override
            public void onTick(long l) {
//                mViewModel.setTime((int) (l / 1000));
                timerView.tim((int) (l / 1000));
            }

            @Override
            public void onFinish() {
                mConnect.unBindService();
                timerView.mFlag(false);
                startFragment.mStartFragment3();
//                startFragment3();
            }
        }.start();
    }


    private void startFragment3() {
        Fragment fragment = ListFragment.newInstance();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fm = fragmentManager.beginTransaction();
        fm.replace(R.id.activity_admin, fragment);
        fm.commit();
    }

    public static SearchFragment newInstance() {
        Bundle arg = new Bundle();
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            startFragment = (StartFragment3) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement onSomeEventListener");
        }
    }
}
