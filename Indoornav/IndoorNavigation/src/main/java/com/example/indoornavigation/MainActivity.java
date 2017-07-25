package com.example.indoornavigation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView mText;
    KalmanFilter kalmanFilter = new KalmanFilter(0.01f, 3.0f);
    float kx=0.0f;
    float ky=0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mText=(TextView)findViewById(R.id.text1);
        mText.setText("");
        kx=34;
        ky=23;
    }

    public void onMap(View view) {
        Intent intent = new Intent(MainActivity.this,MapActivity.class);
        //intent.putExtra("MX",kx);
        //intent.putExtra("MY",ky);
        startActivity(intent);
        //float filteredRssi = kalmanFilter.filter(-73);
        //mText.append(String.valueOf(filteredRssi)+"\n");
    }
}
