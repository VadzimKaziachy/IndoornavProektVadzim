package by.grsu.ftf.indoornav.navigation.map.fragment_0_map;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.indoornav.R;

/**
 * Created by Vadzim on 20.03.2018.
 */

public class InitialWindow extends Fragment {


    private InitialWindowView initialWindowView;
    private Animation anim_exposit;

    private InitialWindowViewProgress progress;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_0_map, container, false);
        setRetainInstance(true);
//        initialWindowView = (InitialWindowView) view.findViewById(R.id.initialWindowView);
        progress = (InitialWindowViewProgress) view.findViewById(R.id.progress);
        anim_exposit = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_exposit);
//        if (savedInstanceState == null) {
//            initialWindowView.startAnimation(anim_exposit);
//        }
        return view;
    }

    public static InitialWindow newInstance() {
        Bundle arg = new Bundle();
        InitialWindow fragment = new InitialWindow();
        fragment.setArguments(arg);
        return fragment;
    }
}
