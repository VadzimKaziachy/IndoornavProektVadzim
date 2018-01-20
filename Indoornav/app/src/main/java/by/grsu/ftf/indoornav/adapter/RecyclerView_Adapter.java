package by.grsu.ftf.indoornav.adapter;


import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.indoornav.R;

import java.util.List;

import by.grsu.ftf.indoornav.Beacon.Beacon;

/**
 * Created by Вадим on 22.10.2017.
 */

public class RecyclerView_Adapter extends RecyclerView.Adapter<RecyclerView_Adapter.ViewHolder> {
    private List<Beacon> beacon;
    private static ClickListener clickListener;

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
        holder.bind(beacon);
    }

    @Override
    public int getItemCount() {
        if (beacon == null) return 0;
        return beacon.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView beacons;
        private TextView X;
        private TextView Y;
        private Beacon mBeacon;
        private ProgressRSSI progressRSSI;


        ViewHolder(View itemView) {
            super(itemView);
            beacons = (TextView) itemView.findViewById(R.id.beacon);
            progressRSSI = (ProgressRSSI) itemView.findViewById(R.id.progressRSSI);
            X = (TextView) itemView.findViewById(R.id.X);
            Y = (TextView) itemView.findViewById(R.id.Y);
            itemView.setOnClickListener(this);
        }

        void bind(Beacon beacon) {
            mBeacon = beacon;
            beacons.setText(mBeacon.getId());
            if (mBeacon.getX() != null) {
                X.setText("X = " + mBeacon.getX());
                Y.setText("Y = " + mBeacon.getY());
            }
            progressRSSI.setRSSI(mBeacon.getProgressRSSI());
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(mBeacon, view);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        RecyclerView_Adapter.clickListener = clickListener;
    }
}