package by.grsu.ftf.indoornav.administrator.activitySearch.fragment_3_admin;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.indoornav.R;

import java.util.List;

import by.grsu.ftf.indoornav.administrator.activityEntry.EntryActivity;
import by.grsu.ftf.indoornav.administrator.activitySearch.fragment_1_Admin.ButtonFragment;
import by.grsu.ftf.indoornav.db.BeaconViewModel;
import by.grsu.ftf.indoornav.db.beaconAdmin.BeaconAdmin;

/**
 * Created by Vadzim on 05.03.2018.
 */

public class ListFragment extends Fragment {
    private Admin_RecyclerView mRecyclerView;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mManager;
    private BeaconViewModel mViewModel;
    public static final String BEACON_INFO = "BEACON_INFO";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_3_admin, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.adminRecyclerView);
        adapter();
        room();
        return view;
    }

    private void room() {
        mViewModel = ViewModelProviders.of(this).get(BeaconViewModel.class);
        mViewModel.getBeaconAdmin().observe(this, new Observer<List<BeaconAdmin>>() {
            @Override
            public void onChanged(@Nullable List<BeaconAdmin> mBeacon) {
                if(mBeacon.size() == 0){
                    startFragment3();
                } else {
                    mRecyclerView.setBeacon(mBeacon);
                    mRecyclerView.notifyDataSetChanged();
                }
            }
        });
    }
    private void startFragment3(){
        Fragment fragment = ButtonFragment.newInstance();
        FragmentTransaction fm = getFragmentManager().beginTransaction();
        fm.replace(R.id.activity_admin, fragment);
        fm.commit();
    }

    private void adapter() {
        recyclerView.setHasFixedSize(true);
        mManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mManager);
        mRecyclerView = new Admin_RecyclerView();
        recyclerView.setAdapter(mRecyclerView);
        mRecyclerView.setOnItemClickListener(new ClickListenerAdmin() {
            @Override
            public void onItemClick(BeaconAdmin beacon, View view) {
                Intent intent = new Intent(getActivity(), EntryActivity.class);
                intent.putExtra(BEACON_INFO, beacon);
                startActivity(intent);
            }
        });
    }

    public static ListFragment newInstance() {
        Bundle arg = new Bundle();
        ListFragment fragment = new ListFragment();
        fragment.setArguments(arg);
        return fragment;
    }
}
