package by.grsu.ftf.indoornav.beaconInfo;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.indoornav.R;

import by.grsu.ftf.indoornav.MainActivity;

/**
 * Created by Vadzim on 28.11.2017.
 */

public class BeaconFragment extends Fragment {
    TextView textView;
    private static final String ARG_BEACON_ID = "ARG_BEACON_ID";
    private String beacon_id;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beacon_id = (String) getArguments().getSerializable(ARG_BEACON_ID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beacon, container, false);
        textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(beacon_id);
        return view;
    }

    public static BeaconFragment newInstance(String beacon) {
        Bundle arg = new Bundle();
        arg.putSerializable(ARG_BEACON_ID, beacon);

        BeaconFragment fragment = new BeaconFragment();
        fragment.setArguments(arg);
        return fragment;
    }
}
