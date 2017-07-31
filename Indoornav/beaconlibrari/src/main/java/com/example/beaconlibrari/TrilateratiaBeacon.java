package com.example.beaconlibrari;

/**
 * Created by Вадим on 25.07.2017.
 */

public class TrilateratiaBeacon {
    public static final String trilateratiaBeacon = "2.2.1 TrilateratiaBeacon " + FilterKalman.map;
}
//здесь будет определяться местоположение человека в помещение, данные буду приходить из класса FilterKalman
//после чего по силе сигнала будем определять расстояние до Beacon и по трем Beacon будем определять координаты человека
//после чего значение x и y будет отправляться в класс TypeBeacon, а оттуда в главный класс MainActivity