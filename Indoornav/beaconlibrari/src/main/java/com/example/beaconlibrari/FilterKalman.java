package com.example.beaconlibrari;

/**
 * Created by Вадим on 25.07.2017.
 */

public class FilterKalman {
    private static String map = "3.FilterKalman";
    public static String getFilterKalman(){
        return map;
    }
}
//сюда будут приходить данные из класса TypeBeacon, при случаи если тип бикана не определися, то есть не одни из сторонних SDK
//не подходит. Сигнал будет тут усредняться и сглаживаться, чтоб не было сильных расхождение в сигнале, после чего усредненый
//сигнал будет поступать в класс TrilateratiaBeacon где пос силе сигнала будет определяться расстояние до Beacon и в дальнейшем
//будет находиться по трем Beacon координаты местоположения человека.