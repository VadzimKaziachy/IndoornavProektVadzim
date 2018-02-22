package by.grsu.ftf.indoornav.administrator.activitySearch;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.indoornav.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import by.grsu.ftf.beaconlib.BeaconControllerService;
import by.grsu.ftf.indoornav.administrator.ConnectionService;
import by.grsu.ftf.indoornav.administrator.activityEntry.EntryActivity;
import by.grsu.ftf.indoornav.db.BeaconViewModel;
import by.grsu.ftf.indoornav.db.beaconAdmin.BeaconAdmin;

public class SearchActivity extends AppCompatActivity implements BeaconControllerService.Callbacks {

    private Button button;
    private TextView textView;
    private BeaconViewModel mViewModel;
    private Admin_RecyclerView mRecycler;
    private RecyclerView.LayoutManager mManager;
    private RecyclerView recyclerView;
    private List<BeaconAdmin> mBeacon;
    private ConnectionService mConnect;
    private Integer time;
    private BeaconAdmin mBeaconAdmin;
    public static final String BEACON_INFO = "BEACON_INFO";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        initViews();

        room(savedInstanceState);
        connectionService();
        adapter();

        clickListener();
    }

    private void initViews() {
        button = (Button) findViewById(R.id.button2);
        textView = (TextView) findViewById(R.id.textView);
        recyclerView = (RecyclerView) findViewById(R.id.adminRecyclerView);

        mBeacon = new ArrayList<>();
        time = 5;
    }

    private void room(Bundle savedInstanceState){
        mViewModel = ViewModelProviders.of(this).get(BeaconViewModel.class);
        if(savedInstanceState == null ){
            mViewModel.deleteBeaconAdminAll();
        }
        mViewModel.getBeaconAdmin().observe(this, new Observer<List<BeaconAdmin>>() {
            @Override
            public void onChanged(@Nullable List<BeaconAdmin> beacon) {
                mBeacon = beacon;
                notifyAdapter();
            }
        });
    }

    private void connectionService() {
        mConnect = new ConnectionService();
        mConnect.mConnection(this);
    }

    private void adapter() {
        recyclerView.setHasFixedSize(true);
        mManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mManager);
        mRecycler = new Admin_RecyclerView();
        recyclerView.setAdapter(mRecycler);
        mRecycler.setOnItemClickListener(new ClickListenerAdmin() {
            @Override
            public void onItemClick(BeaconAdmin beacon, View view) {
                Intent intent = new Intent(SearchActivity.this, EntryActivity.class);
                intent.putExtra(BEACON_INFO, beacon);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mViewModel.getTime() != null && mViewModel.getTime() != 1) {
            time = mViewModel.getTime();
            timerBeacon();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mConnect.unBindService();
    }

    private void clickListener() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               timerBeacon();
            }
        });
    }


    @Override
    public void updateClient(List<String> list) {
        mBeaconAdmin = new BeaconAdmin();
        mBeaconAdmin.setId(Integer.parseInt(list.get(0).substring(2)));
        mBeaconAdmin.setName(list.get(0));
        mBeaconAdmin.setRSSI(list.get(1));
        mViewModel.addBeaconAdmin(mBeaconAdmin);

    }

    private void notifyAdapter() {
        mRecycler.setBeacon(mBeacon);
        mRecycler.notifyDataSetChanged();
    }

    private void timerBeacon(){
        mConnect.bindService();
        new CountDownTimer(5 * 1000, 1000) {

            @Override
            public void onTick(long l) {
                mViewModel.setTime((int) (l / 1000));
                textView.setText("осталась = " + l / 1000);
            }

            @Override
            public void onFinish() {
                textView.setText("конец");
                mConnect.unBindService();
            }
        }.start();
    }
}
