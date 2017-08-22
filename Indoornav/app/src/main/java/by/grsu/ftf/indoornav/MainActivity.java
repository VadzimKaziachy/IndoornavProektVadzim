package by.grsu.ftf.indoornav;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.indoornav.R;

import by.grsu.ftf.beacomlib.Bluetooth;
import by.grsu.ftf.indoornav.navigation.map.MapProcessor;
import by.grsu.ftf.indoornav.widget.PathCalculator;


/*
 * этот класс являеться главным, неким ядром программы.
 * программа при запуске переходит в класс Bluetooth где проверяет включин ли Bluetooth
 */

public class MainActivity extends AppCompatActivity {

    public static final String KEY_INTENT_FILTER = "KEY_INTENT_FILTER";
    public static final String KEY_VALUE_MAPPROCESSOR = "KEY_VALUE_MAPPROCESSOR";
    public static final String KEY_VALUE_PATHCALCULATOR = "KEY_VALUE_PATHCALCULATOR";
    public static final String KEY_VALUE_BLUTOOTH = "KEY_VALUE_BLUTOOTH";


    private BroadcastReceiver BROADCAST;

    private TextView textView;
    private TextView textView1;
    private Button button;

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
        textView = (TextView) findViewById(R.id.textView);
        textView1 = (TextView) findViewById(R.id.textView1);
        button = (Button) findViewById(R.id.button);
    }

    private void regReceiver() {
        registerReceiver(BROADCAST, new IntentFilter(KEY_INTENT_FILTER));
    }

    @Override
    protected void onStart() {
        super.onStart();
        regReceiver();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(BROADCAST);
    }

    private void setOnClickListeners() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this, MapProcessor.class));
                startService(new Intent(MainActivity.this, PathCalculator.class));
                startService(new Intent(MainActivity.this, Bluetooth.class));
            }
        });
    }

    private void initBroadcast() {
        BROADCAST = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.hasExtra(KEY_VALUE_MAPPROCESSOR)) {
                    String BLUETOOTH = intent.getStringExtra(KEY_VALUE_MAPPROCESSOR);
                    textView.setText(BLUETOOTH);
                } else if (intent.hasExtra(KEY_VALUE_PATHCALCULATOR)) {
                    String PATHCALCULATOR = intent.getStringExtra(KEY_VALUE_PATHCALCULATOR);
                    textView1.setText(PATHCALCULATOR);
                }
            }
        };

    }
}


//интрентс сервис
// сорстрим,

