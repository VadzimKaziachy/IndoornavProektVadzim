package by.grsu.ftf.indoornav;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.indoornav.R;

import java.util.ArrayList;
import java.util.List;


import by.grsu.ftf.beaconlib.BeaconControllerService;


/*
 * This class is the main, some kind of kernel program.
 * The program goes to the Bluetooth class at startup, which checks if Bluetooth is turned on.
 */

public class MainActivity extends AppCompatActivity {

    private Button start;
    private Button stop;
    static ListView listView;
    private static List<String> LIST_BEACON = new ArrayList<>();
    static ArrayAdapter<String> mAdapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setOnClickListeners();
    }

    private void initViews() {
        start = (Button) findViewById(R.id.button);
        stop = (Button) findViewById(R.id.button1);
        listView = (ListView) findViewById(R.id.ListView);
        mAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, LIST_BEACON);

    }

    private void setOnClickListeners() {
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this, BeaconControllerService.class));
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this, BeaconControllerService.class));
            }
        });
    }

    public static void List(ArrayList<String> list) {
        LIST_BEACON.clear();
        for(int i = 0; i<list.size(); i++){
            LIST_BEACON.add(list.get(i));
        }
        listView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}




