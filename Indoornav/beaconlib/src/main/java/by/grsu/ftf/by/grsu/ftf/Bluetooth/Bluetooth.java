package by.grsu.ftf.by.grsu.ftf.Bluetooth;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;


/**
 * Created by Вадим on 25.07.2017.
 * здесь будет проверяться, включил ли Bluetooth на тулефоне, если нет то будет  делать запрос на включение Bluetooth
 * если Bluetooth включен то тогда будет производиться поиск устройства после чего будет считывать вся информация с Beacon
 * а именно силу сигнала, minor major.
 * после чего передает все эти данные в главный класс, который в дальнейшем передает эти данные в класс TypeBeacon
 */

public class Bluetooth extends Service {
    private static final String BLUETOOTH = "BLUETOOTH";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
