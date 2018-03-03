package by.grsu.ftf.indoornav.db.classesAssistant;

/**
 * Created by Vadzim on 02.03.2018.
 */

public class BeaconFireBase {

    private String name;
    private Float x;
    private Float y;
    private Float maxDist;

    public Float getMaxDist() {
        return maxDist;
    }

    public void setMaxDist(Float maxDist) {
        this.maxDist = maxDist;
    }

    public Float getY() {

        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public Float getX() {

        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
