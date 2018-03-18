package by.grsu.ftf.indoornav.navigation.map.fragment_1_map;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.indoornav.R;

/**
 * Created by Vadzim on 17.03.2018.
 */

public class ListMap extends Fragment {

    private Map_RecyclerView mMapRecyclerView;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mManager;
    private StartFragment2Map startFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1_map, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.map_recyclerView);
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

    public static ListMap newInstance(){
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
}
