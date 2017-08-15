package Bluetooth;

/**
 * Created by Вадим on 25.07.2017.
 * здесь будет проверяться, включил ли Bluetooth на тулефоне, если нет то будет  делать запрос на включение Bluetooth
 * если Bluetooth включен то тогда будет производиться поиск устройства после чего будет считывать вся информация с Beacon
 * а именно силу сигнала, minor major.
 * после чего передает все эти данные в главный класс, который в дальнейшем передает эти данные в класс TypeBeacon
 */

public class Bluetooth {
    private String BLUETOOTH;

    public Bluetooth(String BLUETOOTH) {
        this.BLUETOOTH = BLUETOOTH;
    }

    public String getBLUETOOTH() {
        return BLUETOOTH;
    }
}
