package by.grsu.ftf.indoornav.beaconInfo;

import android.app.Fragment;

import by.grsu.ftf.indoornav.db.Beacon;
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
