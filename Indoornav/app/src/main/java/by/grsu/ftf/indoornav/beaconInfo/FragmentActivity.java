package by.grsu.ftf.indoornav.beaconInfo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.indoornav.R;

import by.grsu.ftf.indoornav.Beacon.Beacon;
import by.grsu.ftf.indoornav.MainActivity;

/**
 * Created by Vadzim on 28.11.2017.
 */

public class FragmentActivity extends SingleFragmentActivity{
    @Override
    protected Fragment createFragment() {
        Beacon beacon = (Beacon) getIntent().getSerializableExtra(MainActivity.BEACON_FRAGMENT);
        return  new BeaconFragment().newInstance(beacon);
    }
}
