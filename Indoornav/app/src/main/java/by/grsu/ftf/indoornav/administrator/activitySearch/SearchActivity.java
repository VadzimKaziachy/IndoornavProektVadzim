package by.grsu.ftf.indoornav.administrator.activitySearch;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.indoornav.R;

import java.util.List;

import by.grsu.ftf.beaconlib.BeaconControllerService;
import by.grsu.ftf.indoornav.administrator.activitySearch.fragment_1_Admin.ButtonFragment;
import by.grsu.ftf.indoornav.administrator.activitySearch.fragment_1_Admin.StartFragment2;
import by.grsu.ftf.indoornav.administrator.activitySearch.fragment_2_Admin.SearchFragment;
import by.grsu.ftf.indoornav.administrator.activitySearch.fragment_2_Admin.StartFragment3;
import by.grsu.ftf.indoornav.administrator.activitySearch.fragment_3_admin.ListFragment;
import by.grsu.ftf.indoornav.administrator.activitySearch.fragment_3_admin.StartFragment1;
import by.grsu.ftf.indoornav.db.BeaconViewModel;
import by.grsu.ftf.indoornav.db.beaconAdmin.BeaconAdmin;

public class SearchActivity extends AppCompatActivity implements BeaconControllerService.Callbacks,
        StartFragment1,
        StartFragment2,
        StartFragment3 {

    private BeaconViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        if (savedInstanceState == null) {
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = ButtonFragment.newInstance();
            fm.beginTransaction()
                    .add(R.id.activity_admin, fragment)
                    .commit();
        }

        room(savedInstanceState);
    }

    private void room(Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(BeaconViewModel.class);
        if (savedInstanceState == null) {
            mViewModel.deleteBeaconAdminAll();
        }
    }

    @Override
    public void updateClient(List<String> list) {
        BeaconAdmin mBeaconAdmin = new BeaconAdmin();
        mBeaconAdmin.setId(Integer.parseInt(list.get(0).substring(2)));
        mBeaconAdmin.setName(list.get(0));
        mBeaconAdmin.setRSSI(list.get(1));
        mViewModel.addBeaconAdmin(mBeaconAdmin);
    }

    @Override
    public void mStartFragment1() {
        Fragment fragment = ButtonFragment.newInstance();
        FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
        fm.replace(R.id.activity_admin, fragment);
        fm.commit();
    }

    @Override
    public void mStartFragment2() {
        Fragment startFragment = SearchFragment.newInstance();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.activity_admin, startFragment);
        ft.commit();
    }

    @Override
    public void mStartFragment3() {
        Fragment fragment = ListFragment.newInstance();
        FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
        fm.replace(R.id.activity_admin, fragment);
        fm.commit();
    }
}
