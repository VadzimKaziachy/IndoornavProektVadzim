package by.grsu.ftf.indoornav.util;

/**
 * Created by Вадим on 25.07.2017.
 * в этом классе будет определяться координаты местоположения человека в комнате, при случаи если все биканы разные
 * если все биканы одинаковые(то есть одной фирмы) то тогда этот класс не будет использоваться
 */

public class Trilateration {
    String TRLATERATION;

    public Trilateration(String TRLATERATION) {
        this.TRLATERATION = TRLATERATION;
    }

    public String getTRLATERATION() {
        return TRLATERATION;
    }
}
