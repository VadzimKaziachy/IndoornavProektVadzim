package by.grsu.ftf.beacomlib;

/**
 * Created by Вадим on 25.07.2017.
 * сюда будут приходить данные из класса BeaconController, при случаи если тип бикана не определися, то есть не одни из сторонних SDK
 * не подходит. Сигнал будет тут усредняться и сглаживаться, чтоб не было сильных расхождение в сигнале, после чего усредненый
 * сигнал будет поступать в класс TrilateratiaBeacon где пос силе сигнала будет определяться расстояние до Beacon и в дальнейшем
 * будет находиться по трем Beacon координаты местоположения человека.
 */

public class FilterKalman {
   private String FILTERKALMAN;

    public FilterKalman(String FILTERKALMAN) {
        this.FILTERKALMAN = FILTERKALMAN;
    }

    public String getFILTERKALMAN() {
        return FILTERKALMAN;
    }
}
