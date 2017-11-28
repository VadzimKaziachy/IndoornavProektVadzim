package by.grsu.ftf.indoornav.beaconInfo;

import android.app.Fragment;;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.indoornav.R;

/**
 * Created by Vadzim on 28.11.2017.
 */

public class BeaconFragment extends Fragment {
    TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beacon, container, false);
        textView = (TextView) view.findViewById(R.id.textView);
        textView.setText("Beacon");
        return view;
    }
}
