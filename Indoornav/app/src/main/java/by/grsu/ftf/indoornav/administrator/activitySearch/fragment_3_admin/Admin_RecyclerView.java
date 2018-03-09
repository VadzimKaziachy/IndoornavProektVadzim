package by.grsu.ftf.indoornav.administrator.activitySearch.fragment_3_admin;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.indoornav.R;

import java.util.List;

import by.grsu.ftf.indoornav.db.beaconAdmin.BeaconAdmin;

/**
 * Created by Vadzim on 17.02.2018.
 */

public class Admin_RecyclerView extends RecyclerView.Adapter<Admin_RecyclerView.ViewHolder> {
    private List<BeaconAdmin> mBeacon;
    private static ClickListenerAdmin clickListener;

    public void setBeacon(List<BeaconAdmin> mBeacon) {
        this.mBeacon = mBeacon;
    }

    @Override
    public Admin_RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.admin_recycler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Admin_RecyclerView.ViewHolder holder, int position) {
        BeaconAdmin beacon = this.mBeacon.get(position);
        holder.bind(beacon);
    }

    @Override
    public int getItemCount() {
        if (mBeacon == null) return 0;
        return mBeacon.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView textView;
        private BeaconAdmin beacon;

        ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.beaconAdmin);
            itemView.setOnClickListener(this);
        }

        void bind(BeaconAdmin beacon) {
            this.beacon = beacon;
            textView.setText(beacon.getName());
        }


        @Override
        public void onClick(View view) {
            clickListener.onItemClick(this.beacon, view);
            Log.d("Log", "нажал в адаптере");
        }
    }
    public void setOnItemClickListener(ClickListenerAdmin clickListener) {
        Admin_RecyclerView.clickListener = clickListener;
    }
}
