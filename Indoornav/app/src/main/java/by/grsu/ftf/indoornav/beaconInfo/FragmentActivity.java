package by.grsu.ftf.indoornav.beaconInfo;

import android.app.Fragment;

import by.grsu.ftf.indoornav.MainActivity;

/**
 * Created by Vadzim on 28.11.2017.
 */

public class FragmentActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        String beacon_id = (String) getIntent().getSerializableExtra(MainActivity.BEACON_FRAGMENT);
        return new BeaconFragment().newInstance(beacon_id);
    }
}
