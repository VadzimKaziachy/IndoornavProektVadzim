package by.grsu.ftf.indoornav.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Вадим on 05.10.2017.
 */

public class Adapter extends ArrayAdapter<String> {

    private static String[] beacon_adapter = null;
    private Context mContext;

    public Adapter(Context context, int textViewResourceId,List<String> beacon_list) {
        super(context, textViewResourceId, beacon_list.toArray(new String[beacon_list.size()]));
        this.mContext = context;
        String[] beacon = beacon_list.toArray(new String[beacon_list.size()]);
        beacon_adapter = beacon;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView,@NonNull ViewGroup parent) {
        TextView textView = (TextView) convertView;

        if (convertView == null) {
            convertView = new TextView(mContext);
            textView = (TextView) convertView;
        }
        textView.setText(beacon_adapter[position]);
        return convertView;
    }
}