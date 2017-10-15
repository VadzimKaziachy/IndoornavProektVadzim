package by.grsu.ftf.indoornav.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.indoornav.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Вадим on 05.10.2017.
 */

public class Adapter extends ArrayAdapter<String> {

    private List<String> beacon_adapter = new ArrayList<>();
    private List<String> beacon_distance = new ArrayList<>();

    public Adapter(Context context, List<String> list_beacon, List<String> list_distance) {
        super(context, -1, list_beacon);
        beacon_adapter.clear();
        beacon_adapter = list_beacon;
        beacon_distance = list_distance;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)
                getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.rowlayout, parent, false);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        TextView textView1 = (TextView) view.findViewById(R.id.textView2);
        textView.setText(beacon_adapter.get(position));
        textView1.setText(beacon_distance.get(position));
        return view;
    }
}