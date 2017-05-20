package edu.web.util;

import edu.web.model.entities.Coordinate;

/**
 * Created by stalk on 23.04.2017.
 */
public class Convert {
    public static Coordinate convert(char x, int y) {
        Coordinate A = new Coordinate();
        if (x <= 'A') {
            A.setXx(x - 'A');
        } else {
            A.setXx(x - 'a');
        }
        A.setYy(y - 1);
        return A;
    }

    public static char[] convertBack(Coordinate A) {
        int xx = A.getXx();
        int yy = A.getYy();
        char[] result = new char[2];

        result[0] = (char) ('A' + xx);
        result[1] = (char) (yy+1);

        return result;
    }
}
