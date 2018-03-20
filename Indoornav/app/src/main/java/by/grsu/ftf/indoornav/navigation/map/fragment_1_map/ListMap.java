package by.grsu.ftf.indoornav.navigation.map.fragment_1_map;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.indoornav.R;

import java.util.List;

import by.grsu.ftf.indoornav.MainActivity;
import by.grsu.ftf.indoornav.db.BeaconViewModel;
import by.grsu.ftf.indoornav.storage.DataBaseFireBase;

/**
 * Created by Vadzim on 17.03.2018.
 */

public class ListMap extends Fragment implements DataBaseFireBaseFragmentMap.Callback {

    private Map_RecyclerView mMapRecyclerView;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mManager;
    private StartFragment2Map startFragment;
    private BeaconViewModel beaconViewModel;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1_map, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.map_recyclerView);
        beaconViewModel = ViewModelProviders.of(this).get(BeaconViewModel.class);
        adapter();
        return view;
    }

    private void adapter() {
        recyclerView.setHasFixedSize(true);
        mManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mManager);
        mMapRecyclerView = new Map_RecyclerView();
        recyclerView.setAdapter(mMapRecyclerView);
        mMapRecyclerView.setOnItemClickListener(new ClickListenerMap() {
            @Override
            public void onItemClick(String s, View view) {
                startFragment.startFragment2();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (isOnline(getContext())) {
            if (beaconViewModel.getList_zal() == null) {
                DataBaseFireBaseFragmentMap dataBase = new DataBaseFireBaseFragmentMap(this);
                dataBase.dataBaseFireBase(getContext());
            }
        }
    }

    public static ListMap newInstance() {
        Bundle arg = new Bundle();
        ListMap fragment = new ListMap();
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            startFragment = (StartFragment2Map) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement StartFragment2");
        }
    }

    @Override
    public void mCallback(List<String> mList_Zal) {
//        Log.d("Log", "ListMap, list_zal " + mList_Zal);
//        if (mList_Zal != null) {
//            mMapRecyclerView.setList_hall(mList_Zal);
//            mMapRecyclerView.notifyDataSetChanged();
//        }
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
