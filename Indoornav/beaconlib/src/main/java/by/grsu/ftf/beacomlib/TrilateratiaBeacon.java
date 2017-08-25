package by.grsu.ftf.beacomlib;

import java.util.ArrayList;


/**
 * Created by Вадим on 25.07.2017.
 * здесь будет определяться местоположение человека в помещение, данные буду приходить из класса FilterKalman
 * после чего по силе сигнала будем определять расстояние до Beacon и по трем Beacon будем определять координаты человека
 * после чего значение x и y будет отправляться в класс BeaconController, а оттуда в главный класс MainActivity
 */

public class TrilateratiaBeacon {
    private ArrayList<String> list = new ArrayList<>();

    void setList(ArrayList<String> list) {
        this.list = list;

    }

    public ArrayList<String> getList() {
        return list;
    }
}
