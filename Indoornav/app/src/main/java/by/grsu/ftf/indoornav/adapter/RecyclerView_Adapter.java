package by.grsu.ftf.indoornav.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.indoornav.R;

import java.util.List;

import by.grsu.ftf.indoornav.Beacon.Beacon;
import by.grsu.ftf.indoornav.beaconInfo.BeaconFragment;

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
        holder.bindViewHolder(beacon);
    }

    @Override
    public int getItemCount() {
        if (beacon == null) {
            return 0;
        } else {
            return beacon.size();
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder  {
        private TextView beacons;
        private ProgressRSSI progressRSSI;
        private RSSIspeedometer rssIspeedometer;
        private Beacon mBeacon;

        ViewHolder(View itemView) {
            super(itemView);
            beacons = (TextView) itemView.findViewById(R.id.beacon);
            progressRSSI = (ProgressRSSI) itemView.findViewById(R.id.progressRSSI);
            rssIspeedometer = (RSSIspeedometer) itemView.findViewById(R.id.speedometerRSSI);
        }

        void bindViewHolder(Beacon beacon) {
            mBeacon = beacon;
            beacons.setText(mBeacon.getId());
            progressRSSI.setRSSI(mBeacon.getProgressRSSI(), mBeacon.getRSSI());
            rssIspeedometer.setAngleRSSI(mBeacon.getProgressRSSI());
        }
    }
}