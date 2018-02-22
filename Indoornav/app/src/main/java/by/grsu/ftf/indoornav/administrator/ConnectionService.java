package by.grsu.ftf.indoornav.administrator;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import by.grsu.ftf.beaconlib.BeaconControllerService;
import by.grsu.ftf.indoornav.administrator.activitySearch.SearchActivity;

/**
 * Created by Vadzim on 18.02.2018.
 */

public class ConnectionService  {

    private Context context;
    private boolean mBound;

    public void mConnection(Context context) {
        this.context = context;
    }

    public void bindService() {
        context.bindService(new Intent(context, BeaconControllerService.class),
                mConnection, Context.BIND_AUTO_CREATE);
    }

    public void unBindService() {
        if (mBound) {
            context.unbindService(mConnection);
            mBound = false;
        }

    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BeaconControllerService.MyBinder binder = (BeaconControllerService.MyBinder) service;
            binder.connectCallbacks((BeaconControllerService.Callbacks) context);
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };
}
