package by.grsu.ftf.indoornav.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.indoornav.R;

import java.util.List;

import by.grsu.ftf.indoornav.util.Beacon;

/**
 * Created by Вадим on 22.10.2017.
 */

public class RecyclerView_Adapter extends RecyclerView.Adapter<RecyclerView_Adapter.ViewHolder> {
    private List<Beacon> beacon;

    public void setBeacon(List<Beacon> beacon) {
        this.beacon = beacon;
    }

    @Override
    public RecyclerView_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rowlayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Beacon beacon = this.beacon.get(position);
        holder.beacon.setText(beacon.getId());
        holder.rssi.setText(beacon.getRSSI());
        holder.distance.setText(beacon.getDistance());
        holder.progressRSSI.setRSSI(beacon.getRSSI());
    }

    @Override
    public int getItemCount() {
        if (beacon == null) {
            return 0;
        } else {
            return beacon.size();
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView beacon, rssi,distance;
        private ProgressRSSI progressRSSI;

        ViewHolder(View itemView) {
            super(itemView);
            beacon = (TextView) itemView.findViewById(R.id.beacon);
            rssi = (TextView) itemView.findViewById(R.id.RSSI);
            distance = (TextView) itemView.findViewById(R.id.distance);
            progressRSSI = (ProgressRSSI) itemView.findViewById(R.id.progressRSSI);
        }
    }
}