package com.example.indoornav;

/**
 * Created by Вадим on 25.07.2017.
 */

public class WaveAlgorithm {
    private static String waveAlgorithm = "4.WaveAlgorithm";
    public static String getWaveAlgorithm(){
        return waveAlgorithm;
    }
}
//суда будет приходить карта с класс MainActivity по которой будет строиться путь из точки A в точку Б. Координаты точки А будут
//поступать из главного класс до этого расчитаные в классе Trilateration. Координаты точки Б будту поступать от пользователя
//после получения всех данных с помощью волновой функции будет строиться кратчайший путь из точки А в точку Б и отображаться в
//layot.