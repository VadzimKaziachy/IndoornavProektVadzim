package by.grsu.ftf.indoornav.beaconInfo;

import android.app.Fragment;

/**
 * Created by Vadzim on 28.11.2017.
 */

public class FragmentActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new BeaconFragment();
    }
}
