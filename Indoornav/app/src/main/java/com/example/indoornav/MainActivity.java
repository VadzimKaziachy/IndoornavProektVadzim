package com.example.indoornav;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.beaconlibrari.Distance;

public class MainActivity extends AppCompatActivity {
    TextView textView, textView1, textView2, textView3 ,textView4, textView5, textView6, textView7, textView8, textView9;




    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        one();
        two();

        Bluetooth bluetooth = new Bluetooth();//велючаем Bluethooth
        bluetooth.startScannerBluethooth();

        Distance typeBeacon = new Distance();//задаем место положение beacon
        typeBeacon.setCoord();

        ChangeOfPosition();
    }


    public void one(){
        textView = (TextView) findViewById(R.id.textView);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView6 = (TextView) findViewById(R.id.textView6);
        textView7 = (TextView) findViewById(R.id.textView7);
        textView8 = (TextView) findViewById(R.id.textView8);
        textView9 = (TextView) findViewById(R.id.textView9);

    }
    public void two(){
        textView.setText(String.valueOf(Bluetooth.Bluetooth));
        textView1.setText(String.valueOf(TypeBeacon.typeBeacon));
        textView2.setText(String.valueOf(Map.map));
        textView3.setText(String.valueOf(WaveAlgorithm.waveAlgorithm));
        textView4.setText(String.valueOf(Distance.Bottom_Left));
        textView5.setText(String.valueOf(Distance.Bottom_Rights));
        textView6.setText(String.valueOf(Distance.Top_Left));
        textView7.setText(String.valueOf(Distance.Top_Rights));
        textView8.setText(String.valueOf(Distance.Value_X1));
        textView9.setText(String.valueOf(Distance.Value_Y1));

    }
    void ChangeOfPosition(){
        ImageView person = (ImageView) findViewById(R.id.imageView6);
        ConstraintLayout.LayoutParams params1 = (ConstraintLayout.LayoutParams) (person.getLayoutParams());
        params1.horizontalBias = ((float) 0.9 / 4) * Distance.Value_X1 + 0.05f;
        params1.verticalBias = 1 - (((float) 0.88 / 6) * Distance.Value_Y1 + 0.02f);
        person.setLayoutParams(params1);
    }

}
//этот класс являеться главным, неким ядром программы.
//программа при запуске переходит в класс Bluetooth где проверяет включин ли Bluetooth

