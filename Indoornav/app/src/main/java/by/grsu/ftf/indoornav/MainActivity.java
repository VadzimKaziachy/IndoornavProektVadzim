package by.grsu.ftf.indoornav;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.indoornav.R;

import by.grsu.ftf.beacomlib.BeaconController;


/*
 * этот класс являеться главным, неким ядром программы.
 * программа при запуске переходит в класс Bluetooth где проверяет включин ли Bluetooth
 */

public class MainActivity extends AppCompatActivity {


    private TextView textView;
    private TextView textView1;
    private Button button;
    private Button button1;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setOnClickListeners();
        iii();
    }

    private void initViews() {
        button = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button1);
    }


    private void setOnClickListeners() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this, BeaconController.class));
                Log.d("Log", "START");
            }
        });

    }
    private void iii(){
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this, BeaconController.class));
                Log.d("Log", "STOP");
            }
        });
    }
}


//интрентс сервис
// сорстрим,

