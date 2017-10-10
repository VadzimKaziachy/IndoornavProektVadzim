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
    private final Context context;

    public Adapter(Context context, List<String> beacon_list) {
        super(context, -1, beacon_list);

        beacon_adapter.clear();
        this.context = context;
        beacon_adapter = beacon_list;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.rowlayout, parent, false);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(beacon_adapter.get(position));
        return view;
    }
}