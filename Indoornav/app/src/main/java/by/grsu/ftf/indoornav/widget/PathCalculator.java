package by.grsu.ftf.indoornav.widget;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import static by.grsu.ftf.indoornav.Broadcast.KEY_VALUE_PATHCALCULATOR;


/**
 * Created by Вадим on 25.07.2017.
 * суда будет приходить карта с класс MainActivity по которой будет строиться путь из точки A в точку Б. Координаты точки А будут
 * поступать из главного класс до этого расчитаные в классе Trilateration. Координаты точки Б будту поступать от пользователя
 * после получения всех данных с помощью волновой функции будет строиться кратчайший путь из точки А в точку Б и отображаться в
 * layot.
 */

public class PathCalculator extends Service {
    private static final String PATHCALCULATOR = "PATHCALCULATOR";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Intent intent1 = new Intent("KEY_INTENT_FILTER");
        intent1.putExtra(KEY_VALUE_PATHCALCULATOR, PATHCALCULATOR);
        sendBroadcast(intent1);

        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
