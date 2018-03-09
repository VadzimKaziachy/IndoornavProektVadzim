package by.grsu.ftf.indoornav.administrator.activitySearch.fragment_1_Admin;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.indoornav.R;

/**
 * Created by Vadzim on 05.03.2018.
 */

public class ButtonFragment extends Fragment {
    private Button startFragment2;
    private StartFragment2 startFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1_admin, container, false);
//        setRetainInstance(true);
        startFragment2 = (Button) view.findViewById(R.id.startFragment2);
        clickListener();
        return view;
    }

    private void clickListener(){
        startFragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startFragment.mStartFragment2();
            }
        });
    }

    public static ButtonFragment newInstance(){
        Bundle arg = new Bundle();
        ButtonFragment fragment = new ButtonFragment();
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            startFragment = (StartFragment2) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement onSomeEventListener");
        }
    }
}
