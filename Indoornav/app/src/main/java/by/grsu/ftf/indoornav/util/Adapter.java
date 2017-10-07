package by.grsu.ftf.indoornav.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Вадим on 05.10.2017.
 */

public class Adapter extends ArrayAdapter<String> {
    private Context context;
    private static List<String> LIST;

    public Adapter(Context context, int textViewResourceId, String beacon) {
        super(context, textViewResourceId);
        this.context = context;
        if (beacon != null) {
            LIST = writeInList(beacon);

        }
    }

    private List<String> writeInList(String beacon) {
        List<String> LIST = new ArrayList<>();
        String[] massif_beacon = beacon.split(",");
        LIST.clear();
        for (int i = 0; i < massif_beacon.length; i++) {
            LIST.add(massif_beacon[i]);
        }
        return LIST;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) convertView;

        if (convertView == null) {
            convertView = new TextView(context);
            textView = (TextView) convertView;
        }
        textView.setText(LIST.get(position));
        return (convertView);
    }
}
