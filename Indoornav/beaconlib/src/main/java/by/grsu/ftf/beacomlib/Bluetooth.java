package by.grsu.ftf.beacomlib;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.TimeUnit;


/**
 * Created by Вадим on 25.07.2017.
 * здесь будет проверяться, включил ли Bluetooth на тулефоне, если нет то будет  делать запрос на включение Bluetooth
 * если Bluetooth включен то тогда будет производиться поиск устройства после чего будет считывать вся информация с Beacon
 * а именно силу сигнала, minor major.
 * после чего передает все эти данные в главный класс, который в дальнейшем передает эти данные в класс TypeBeacon
 */

public class Bluetooth extends Service {
    private static int I = 0;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        someTask();

        return START_NOT_STICKY;
    }
    void someTask() {
        new Thread(new Runnable() {
            public void run() {
                while(true){
                    Log.d("Log", "Bluetooth.class " + (I++));
                    try {
                        TimeUnit.SECONDS.sleep(1);
//                        Intent intent1 = new Intent(KEY_INTENT_FILTER);
//                        intent1.putExtra(KEY_VALUE_MAPPROCESSOR, MAP);
//                        sendBroadcast(intent1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
