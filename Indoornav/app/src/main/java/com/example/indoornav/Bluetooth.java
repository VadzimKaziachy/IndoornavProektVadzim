package com.example.indoornav;

/**
 * Created by Вадим on 25.07.2017.
 */

public class Bluetooth {
    private static String Bluetooth = "1.Bluetooth";

    public static String getBlutooth(){
        return Bluetooth;
    }
}
//здесь будет проверяться, включил ли Bluetooth на тулефоне, если нет то будет  делать запрос на включение Bluetooth
//если Bluetooth включен то тогда будет производиться поиск устройства после чего будет считывать вся информация с Beacon
//а именно силу сигнала, minor major.
//после чего передает все эти данные в главный класс, который в дальнейшем передает эти данные в класс TypeBeacon