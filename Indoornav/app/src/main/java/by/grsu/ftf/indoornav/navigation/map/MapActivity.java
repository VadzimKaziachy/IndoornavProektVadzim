package by.grsu.ftf.indoornav.navigation.map;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.indoornav.R;

import java.util.List;

import by.grsu.ftf.indoornav.Beacon.Beacon;

import static by.grsu.ftf.indoornav.MainActivity.BEACON_MAP;

/**
 * Created by Vadzim on 13.01.2018.
 */

public class MapActivity extends AppCompatActivity {
    private Map map;
    private List<Beacon> beacons;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_map);

        beacons = (List<Beacon>) getIntent().getSerializableExtra(BEACON_MAP);


        map = (Map) findViewById(R.id.map);

        Drawable drawable = getResources().getDrawable(R.drawable.floor);
        map.setBackground(drawable);
        map.provaider(beacons);
    }
}
