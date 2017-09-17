package by.grsu.ftf.indoornav;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.indoornav.R;

import java.util.ArrayList;
import java.util.List;

import by.grsu.ftf.beaconlib.BeaconControllerService;
import by.grsu.ftf.indoornav.storage.Storage;
import by.grsu.ftf.indoornav.storage.TestBeacon;


/*
 * This class is the main, some kind of kernel program.
 * The program goes to the Bluetooth class at startup, which checks if Bluetooth is turned on.
 */

public class MainActivity extends AppCompatActivity {

    private Button start;
    private Button stop;
    private ListView listView;
    private TextView textView; //тут
    private static List<String> LIST_BEACON = new ArrayList<>();
    static ArrayAdapter<String> mAdapter;

    private BroadcastReceiver br;
    public static final String KEY_INTENT_FILTER = "by.grsu.ftf.indoornav.FILTER_ACTIVITY";
    public static final String KEY_VALUE_LIST = "KEY_VALUE_LIST";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setOnClickListeners();
        initBroadcast();
    }

    private void initViews() {
        start = (Button) findViewById(R.id.button);
        stop = (Button) findViewById(R.id.button1);
        listView = (ListView) findViewById(R.id.ListView);
        mAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, LIST_BEACON);
        textView = (TextView) findViewById(R.id.textView); //тут

    }

    private void setOnClickListeners() {
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this, BeaconControllerService.class));
                startService(new Intent(MainActivity.this, TestBeacon.class));
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this, BeaconControllerService.class));
                stopService(new Intent(MainActivity.this, TestBeacon.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        textView.setText(Storage.getRepository(getApplication())); //тут
        Log.d("Log", Storage.getRepository(getApplicationContext()) + " Возрадилcя "); //тут
        regReceiver();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(br);
    }

    private void regReceiver() {
        registerReceiver(br, new IntentFilter(KEY_INTENT_FILTER));
    }

    private void initBroadcast() {
        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.hasExtra(KEY_VALUE_LIST)) {
                    List<String> list = intent.getStringArrayListExtra(KEY_VALUE_LIST);

                    Storage.setRepository(getApplicationContext(), LIST_BEACON + ""); //тут
                    textView.setText(Storage.getRepository(getApplication())); //тут

                    LIST_BEACON.clear();
                    for (int i = 0; i < list.size(); i++) {
                        LIST_BEACON.add(list.get(i));
                    }
                    listView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }
            }
        };
    }
}




