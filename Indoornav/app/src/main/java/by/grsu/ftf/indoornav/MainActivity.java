package by.grsu.ftf.indoornav;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.indoornav.R;

import by.grsu.ftf.beacomlib.Distance;
import by.grsu.ftf.indoornav.navigation.map.MapProcessor;
import by.grsu.ftf.indoornav.util.Trilateration;
import by.grsu.ftf.indoornav.widget.PathCalculator;
import by.grsu.ftf.beacomlib.*;
import Bluetooth.*;


/*
 * этот класс являеться главным, неким ядром программы.
 * программа при запуске переходит в класс Bluetooth где проверяет включин ли Bluetooth
 */

public class MainActivity extends AppCompatActivity {
    TextView textView, textView1, textView2, textView3;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void two(){
        MapProcessor mapProcessor = new MapProcessor("1.MapProcessor ");
        Trilateration trilateration = new Trilateration("2.Trilateration ");
        PathCalculator calculator = new PathCalculator("3.PathCalculator ");
        Distance distance = new Distance("4.Distance ");
        FilterKalman filterKalman = new FilterKalman("5.FilterKalman ");
        BeaconController beaconController = new BeaconController("6.BeaconController ");
        TrilateratiaBeacon trilateratiaBeacon = new TrilateratiaBeacon("7.TrilateratiaBeacon ");
        Bluetooth bluetooth = new Bluetooth("8.Bluetooth ");
        textView.setText(mapProcessor.getMAP() + trilateration.getTRLATERATION() + calculator.getPATHCALULOR() + distance.getDISTANCE()
        + filterKalman.getFILTERKALMAN() + beaconController.getBEACONCONTROLLER() + trilateratiaBeacon.getTRILATERATIABEACON() +
        bluetooth.getBLUETOOTH());


    }

}


