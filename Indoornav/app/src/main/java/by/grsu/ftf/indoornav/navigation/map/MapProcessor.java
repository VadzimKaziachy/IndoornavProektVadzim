package by.grsu.ftf.indoornav.navigation.map;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import static by.grsu.ftf.indoornav.MainActivity.KEY_INTENT_FILTER;
import static by.grsu.ftf.indoornav.MainActivity.KEY_VALUE_MAPPROCESSOR;

/**
 * Created by Вадим on 25.07.2017.
 * <p>
 * этот класс будет отвечать за карты, а именно, после определения местоположеня человека, класс MainActivity будет отправлять
 * в этот класс именя Beacon которые ему удалось найти, сдесь будет определять на каком этаже мы находимся по имени Beacon
 * если приложение было впервые запущенно то этот класс в соотвествии с получеными иминами будет давать запрос на сервер
 * какие карты ему нужны, то есть каждому этажу будут соотвествовать Beacon со своим отличительным номером, если человек меняет
 * этаж, то получив с главного класс новые имена, будет делаться запрос на новые карты соотвествующие этажу.
 * от сервера будут приходить 2 карты, первая которую будет видеть пользователь и вторая, на которой будет строиться маршрут,
 * эти 2 карты после получения их с сервера будут отправляться в главный класс MainActivity. После чего карта которую будет видеть
 * пользователь будет отображаться в layout с местоположением его на карте, а та на которой будет строиться маршрут передвежения
 * будет отправляться в класс WaveAlgorithm куда тоже будут отправляться координаты местоположения человека.
 */

public class MapProcessor extends Service {
    private static final String MAP = "MAP";


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent intent1 = new Intent(KEY_INTENT_FILTER);
        intent1.putExtra(KEY_VALUE_MAPPROCESSOR, MAP);
        sendBroadcast(intent1);
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }
}
