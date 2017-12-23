package by.grsu.ftf.indoornav.beaconInfo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.indoornav.R;

import by.grsu.ftf.indoornav.Beacon.Beacon;

/**
 * Created by Vadzim on 28.11.2017.
 */

public class BeaconFragment extends Fragment {
    TextView fragmentBeacon;
    TextView fragmentRSSI;
    TextView fragmentDistance;
    private static final String ARG_BEACON_ID = "ARG_BEACON_ID";
    private Beacon beacon;
    private Animation animator_beacon;
    private Animation animator_distance;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beacon = (Beacon) getArguments().getSerializable(ARG_BEACON_ID);

        animator_beacon = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_beacon);
        animator_distance = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_beacon_distance);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beacon, container, false);

        fragmentBeacon = (TextView) view.findViewById(R.id.fragmentBeacon);
        fragmentRSSI = (TextView) view.findViewById(R.id.fragmentRSSI);
        fragmentDistance = (TextView) view.findViewById(R.id.fragmentDistans);

        fragmentBeacon.setText(beacon.getId());
        fragmentBeacon.startAnimation(animator_beacon);

        fragmentRSSI.setText(beacon.getDistance());
        fragmentRSSI.startAnimation(animator_beacon);

        fragmentDistance.setText(beacon.getRSSI());
        fragmentDistance.startAnimation(animator_distance);
        return view;
    }

    public static BeaconFragment newInstance(Beacon beacon) {
        Bundle arg = new Bundle();
        arg.putSerializable(ARG_BEACON_ID, beacon);

        BeaconFragment fragment = new BeaconFragment();
        fragment.setArguments(arg);
        return fragment;
    }
}
