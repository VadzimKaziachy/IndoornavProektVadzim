package com.example.indoornav;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView, textView1, textView2, textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        one();
        two();
    }


    public void one(){
        textView = (TextView) findViewById(R.id.textView);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);

    }
    public void two(){
        textView.setText(String.valueOf(Bluetooth.Bluetooth));
        textView1.setText(String.valueOf(TypeBeacon.typeBeacon));
        textView2.setText(String.valueOf(Map.map));
        textView3.setText(String.valueOf(WaveAlgorithm.waveAlgorithm));

    }
}
//этот класс являеться главным, неким ядром программы.
//программа при запуске переходит в класс Bluetooth где проверяет включин ли Bluetooth

