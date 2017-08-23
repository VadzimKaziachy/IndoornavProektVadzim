package by.grsu.ftf.indoornav;

import android.content.Intent;
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
    }

    private void initViews() {
        button = (Button) findViewById(R.id.button);
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
}


//интрентс сервис
// сорстрим,

