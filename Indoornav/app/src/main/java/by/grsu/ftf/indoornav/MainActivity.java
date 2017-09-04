package by.grsu.ftf.indoornav;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.indoornav.R;

import by.grsu.ftf.beacomlib.BeaconControllerService;


/*
 * This class is the main, some kind of kernel program.
 * The program goes to the Bluetooth class at startup, which checks if Bluetooth is turned on.
 */

public class MainActivity extends AppCompatActivity {


    private Button button;
    private Button button1;

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
        button1 = (Button) findViewById(R.id.button1);
    }


    private void setOnClickListeners() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this, BeaconControllerService.class));
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService( new Intent(MainActivity.this, BeaconControllerService.class));
            }
        });

    }
}




