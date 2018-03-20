package by.grsu.ftf.indoornav.navigation.map.fragment_0_map;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.indoornav.R;

/**
 * Created by Vadzim on 20.03.2018.
 */

public class InitialWindow  extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_0_map, container, false);
        return view;
    }
}
