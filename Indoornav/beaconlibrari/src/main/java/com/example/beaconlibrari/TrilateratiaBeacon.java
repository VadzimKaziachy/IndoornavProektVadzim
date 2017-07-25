package com.example.beaconlibrari;

/**
 * Created by Вадим on 25.07.2017.
 */

public class TrilateratiaBeacon {
    private static String trilateratiaBeacon = "3.TrilateratiaBeacon";
    public static String getTrilateratiaBeacon(){
        FilterKalman.getFilterKalman();
        return trilateratiaBeacon;
    }

}
//здесь будет определяться местоположение человека в помещение, данные буду приходить из класса FilterKalman
//после чего по силе сигнала будем определять расстояние до Beacon и по трем Beacon будем определять координаты человека
//после чего значение x и y будет отправляться в класс TypeBeacon, а оттуда в главный класс MainActivity