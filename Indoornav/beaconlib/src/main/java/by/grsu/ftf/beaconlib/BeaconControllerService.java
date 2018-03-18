package by.grsu.ftf.beaconlib;


import android.annotation.SuppressLint;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Вадим on 25.07.2017.
 * This class is the core of the module beaconlib.
 */

public class BeaconControllerService extends Service {

    BeaconSimulator beaconSimulator = new BeaconSimulator();

    private IBinder mBinder = new MyBinder();
    private Handler mHandler = new Handler();
    Callbacks callback;
    boolean serviceRun = true;


    private BluetoothAdapter bluetoothAdapter;
    private BluetoothLeScanner bluetoothLeScanner;
    List<String> beacon;


    private Runnable timeUpdaterRunnable = new Runnable() {
        public void run() {
            if (!bluetoothAdapter.isEnabled()) {
                if (callback != null) {
                    List<String> list_Beacon = beaconSimulator.getList();
                    callback.updateClient(list_Beacon);
                }
            } else {
                startScan();
            }
            mHandler.postDelayed(this, 200);
        }
    };

    @Override
    public void onCreate() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        timeUpdaterRunnable.run();
        startScan();
        super.onCreate();
    }


    @Override
    public void onDestroy() {
        stopScan();
        mHandler.removeCallbacks(timeUpdaterRunnable);
        serviceRun = true;
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class MyBinder extends Binder {
        public void connectCallbacks(Callbacks callbacks) {
            if (serviceRun) {
                callback = callbacks;
                serviceRun = false;
            }

        }
    }

    public interface Callbacks {
        void updateClient(List<String> list);
    }


    private void startScan() {
        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                bluetoothAdapter.startLeScan(LeScanCallback);
            } else {
                bluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner();
                ScanSettings settings = new ScanSettings.Builder()
                        .setReportDelay(0)
                        .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
                        .build();
                bluetoothLeScanner.startScan(null, settings, scanCallback);
            }
        }
    }

    private void stopScan() {
        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                bluetoothAdapter.stopLeScan(LeScanCallback);
            } else {
                bluetoothLeScanner.stopScan(scanCallback);
                bluetoothLeScanner = null;
            }
        }
    }


    //SDK>21
    @SuppressLint("NewApi")
    private ScanCallback scanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            BluetoothDevice device = result.getDevice();
            if (device.getName() != null) {
                beacon = new ArrayList<>();
                beacon.add(device.getName());
                beacon.add(String.valueOf(result.getRssi()));
                callback.updateClient(beacon);
            }
        }
    };

    //SDK<21
    private BluetoothAdapter.LeScanCallback LeScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(final BluetoothDevice device, final int rssi, final byte[] bytes) {
            new Runnable() {
                @Override
                public void run() {
                    if (device.getName() != null) {
                        beacon = new ArrayList<>();
                        beacon.add(device.getName());
                        beacon.add(String.valueOf(rssi));
                        callback.updateClient(beacon);
                    }
                }
            };
        }
    };
}

