package by.grsu.ftf.indoornav.util;

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

public class Adapter_RecyclerView extends RecyclerView.Adapter<Adapter_RecyclerView.ViewHolder>  {
    private List<String> beacon_adapter = new ArrayList<>();
    private List<String> beacon_distance = new ArrayList<>();

    public Adapter_RecyclerView(List<String> beacon_adapter, List<String> beacon_distance) {
        this.beacon_adapter = beacon_adapter;
        this.beacon_distance = beacon_distance;
    }

    @Override
    public Adapter_RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rowlayout, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.beacon.setText(beacon_adapter.get(position));
        holder.distance.setText(beacon_distance.get(position));

    }

    @Override
    public int getItemCount() {
        return beacon_adapter.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView beacon, distance;

        public ViewHolder(View itemView) {
            super(itemView);
            beacon = (TextView) itemView.findViewById(R.id.textView);
            distance = (TextView) itemView.findViewById(R.id.textView2);
        }
    }
}