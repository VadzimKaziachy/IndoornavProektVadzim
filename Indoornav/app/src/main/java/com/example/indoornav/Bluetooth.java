package com.example.indoornav;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by Вадим on 25.07.2017.
 */

class Bluetooth extends MainActivity {
    static final String Bluetooth = "1.Bluetooth";
    BluetoothAdapter mBluetoothAdapter;
    BluetoothLeScanner mBleutoothScanner;
    BluetoothManager mBluetoothManager;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)


    void startScannerBluethooth (){
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mBluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBleutoothScanner = mBluetoothAdapter.getBluetoothLeScanner();
    }
   /* не могу понять как работает поиск устройства, и как передать данные которые он нашел в другой пакет
    public void startDiscovery(View view)
    {
        Timer tim = new Timer();
        TimerTask bthh = new MyTimerTask();
        tim.schedule(bthh, 200, 500);
    }
    class MyTimerTask extends TimerTask
    {
        @Override
        public void run()
        {
            AsyncTask.execute(new Runnable()
            {
                @Override
                public void run()
                {
                    mBleutoothScanner.startScan(leScanCallback);    <- вот этот этап не понимаю
                }
            });
        }
    }
    */
}

//здесь будет проверяться, включил ли Bluetooth на тулефоне, если нет то будет  делать запрос на включение Bluetooth
//если Bluetooth включен то тогда будет производиться поиск устройства после чего будет считывать вся информация с Beacon
//а именно силу сигнала, minor major.
//после чего передает все эти данные в главный класс, который в дальнейшем передает эти данные в класс TypeBeacon