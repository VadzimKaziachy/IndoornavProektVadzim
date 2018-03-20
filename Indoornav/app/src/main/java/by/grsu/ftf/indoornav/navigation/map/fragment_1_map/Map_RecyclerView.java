package by.grsu.ftf.indoornav.navigation.map.fragment_1_map;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.indoornav.R;

import java.util.ArrayList;
import java.util.List;

import by.grsu.ftf.indoornav.adapter.ClickListener;

/**
 * Created by Vadzim on 17.03.2018.
 */

public class Map_RecyclerView extends RecyclerView.Adapter<Map_RecyclerView.ViewHolder> {

    private List<String> list_hall;
    private static ClickListenerMap click;

    public Map_RecyclerView() {
        list_hall = new ArrayList<>();
        list_hall.add("зал A");
        list_hall.add("зал Б");
        list_hall.add("зал В");
        list_hall.add("зал Г");
        list_hall.add("зал Д");
        notifyDataSetChanged();
    }

    public void setList_hall(List<String> mList_hall){
        this.list_hall = mList_hall;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.map_recycler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(this.list_hall.get(position));
    }

    @Override
    public int getItemCount() {
        if (list_hall == null) return 0;
        return list_hall.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textView;
        private String hall;

        ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.hallMap);
            itemView.setOnClickListener(this);
        }

        void bind(String hall) {
            this.hall = hall;
            textView.setText(hall);
        }

        @Override
        public void onClick(View view) {
            click.onItemClick(hall, view);
        }
    }

    public void setOnItemClickListener(ClickListenerMap click){
        Map_RecyclerView.click = click;
    }
}
