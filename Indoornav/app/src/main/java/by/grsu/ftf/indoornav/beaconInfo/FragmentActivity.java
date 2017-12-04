package by.grsu.ftf.indoornav.beaconInfo;

import android.app.Fragment;
import android.util.Log;

import by.grsu.ftf.indoornav.Beacon.Beacon;
import by.grsu.ftf.indoornav.MainActivity;

/**
 * Created by Vadzim on 28.11.2017.
 */

public class FragmentActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        Beacon beacon = getIntent().getParcelableExtra(MainActivity.BEACON_FRAGMENT);
        return new BeaconFragment().newInstance(beacon);
    }
}
