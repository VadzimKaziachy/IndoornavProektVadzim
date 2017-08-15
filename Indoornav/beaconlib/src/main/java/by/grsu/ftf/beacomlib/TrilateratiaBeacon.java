package by.grsu.ftf.beacomlib;

/**
 * Created by Вадим on 25.07.2017.
 * здесь будет определяться местоположение человека в помещение, данные буду приходить из класса FilterKalman
 * после чего по силе сигнала будем определять расстояние до Beacon и по трем Beacon будем определять координаты человека
 * после чего значение x и y будет отправляться в класс BeaconController, а оттуда в главный класс MainActivity
 */

public class TrilateratiaBeacon {
    private String TRILATERATIABEACON;

    public TrilateratiaBeacon(String TRILATERATIABEACON) {
        this.TRILATERATIABEACON = TRILATERATIABEACON;
    }

    public String getTRILATERATIABEACON() {
        return TRILATERATIABEACON;
    }
}
