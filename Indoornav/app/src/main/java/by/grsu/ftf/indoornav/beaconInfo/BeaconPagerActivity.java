package by.grsu.ftf.indoornav.beaconInfo;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.example.indoornav.R;

import java.util.ArrayList;
import java.util.List;

import by.grsu.ftf.indoornav.Beacon.Beacon;
import by.grsu.ftf.indoornav.MainActivity;
import by.grsu.ftf.indoornav.storage.BeaconMerger;

import static by.grsu.ftf.indoornav.MainActivity.*;

/**
 * Created by Vadzim on 28.11.2017.
 */

public class BeaconPagerActivity extends FragmentActivity {
    private ViewPager viewPager;
    private List<Beacon> mBeaconList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beacon_pager);

        String beacon = (String) getIntent().getSerializableExtra(BEACON_FRAGMENT);
        mBeaconList = BeaconMerger.getBeacons();
        viewPager = (ViewPager) findViewById(R.id.activity_beacon_view_pager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Beacon beacon = mBeaconList.get(position);
                return BeaconFragment.newInstance(beacon.getId());
            }

            @Override
            public int getCount() {
                return mBeaconList.size();
            }
        });
        for (Beacon beacon1 : mBeaconList) {
            if (beacon1.getId().equals(beacon)) {
                viewPager.setCurrentItem(mBeaconList.indexOf(beacon1));
                break;
            }
        }
    }
}
