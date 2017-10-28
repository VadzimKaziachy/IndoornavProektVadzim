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
    private List<String> beacon = new ArrayList<>();
    private List<String> beacon_distance = new ArrayList<>();
    private List<String> beacon_rssi = new ArrayList<>();

    private ClickListener<String> clickListener;

    public void setBeacon(List<String> beacon) {
        this.beacon.clear();
        this.beacon_rssi.clear();
        this.beacon_distance.clear();
        for (int i = 0; i < beacon.size() / 3; i++) {
            this.beacon.add(beacon.get(i + i * 2));
            this.beacon_distance.add(beacon.get(2 * i + i + 1));
            this.beacon_rssi.add((beacon.get(2 * i + i + 2)));
        }
    }

    public Adapter_RecyclerView(ClickListener<String> onClickListener) {
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
        holder.beacon.setText(beacon.get(position));
        holder.distance.setText(beacon_distance.get(position));
        holder.rssi.setText(beacon_rssi.get(position));
        holder.progressTextView.setValue(Integer.parseInt(beacon_rssi.get(position)));
        if (holder != null) {
            holder.bind(beacon.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return beacon.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView beacon, distance, rssi;
        private ProgressTextView progressTextView;
        String beacon_position;

        ViewHolder(View itemView, ClickListener<String> clickListener) {
            super(itemView);
            beacon = (TextView) itemView.findViewById(R.id.beacon);
            distance = (TextView) itemView.findViewById(R.id.distance);
            rssi = (TextView) itemView.findViewById(R.id.RSSI);
            progressTextView = (ProgressTextView) itemView.findViewById(R.id.progressTextView);
            if (clickListener != null) {
                clickListener.onClick(beacon, beacon_position);
            }
        }

        void bind(String beacon_position) {
            this.beacon_position = beacon_position;
        }
    }
}