package by.grsu.ftf.indoornav.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.indoornav.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Вадим on 22.10.2017.
 */

public class Adapter_RecyclerView extends RecyclerView.Adapter<Adapter_RecyclerView.ViewHolder> {
    private List<String> beacon_adapter = new ArrayList<>();
    private List<String> beacon_distance = new ArrayList<>();
    private List<String> beacon_rssi = new ArrayList<>();

    private ClickListener<String> clickListener;


    public Adapter_RecyclerView(List<String> beacon_adapter, List<String> beacon_distance,
                                List<String> beacon_rssi, ClickListener<String> onClickListener) {
        this.beacon_adapter = beacon_adapter;
        this.beacon_distance = beacon_distance;
        this.beacon_rssi = beacon_rssi;
        this.clickListener = onClickListener;
    }

    @Override
    public Adapter_RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rowlayout, parent, false);
        return new ViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.beacon.setText(beacon_adapter.get(position));
        holder.distance.setText(beacon_distance.get(position));
        holder.rssi.setText(beacon_rssi.get(position));
        if (holder != null) {
            holder.bind(beacon_adapter.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return beacon_adapter.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView beacon, distance, rssi;
        String beacon_string;

        ViewHolder(View itemView, ClickListener<String> clickListener) {
            super(itemView);
            beacon = (TextView) itemView.findViewById(R.id.beacon);
            distance = (TextView) itemView.findViewById(R.id.distance);
            rssi = (TextView) itemView.findViewById(R.id.RSSI);
            if (clickListener != null) {
                clickListener.onClick(beacon, beacon_string);
            }
        }

        void bind(String beacon_position) {
            beacon_string = beacon_position;
        }
    }
}