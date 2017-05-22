package bs.web.model.entities;

/**
 * Created by stalk on 23.04.2017.
 */
public class Coordinate {
    private int xx, yy;

    public Coordinate() {
        xx = -1;
        yy = -1;
    }

    public Coordinate(int x, int y) {
        xx = x;
        yy = y;
    }

    public int getXx() {
        return xx;
    }

    public void setXx(int xx) {
        this.xx = xx;
    }

    public int getYy() {
        return yy;
    }

    public void setYy(int yy) {
        this.yy = yy;
    }
}