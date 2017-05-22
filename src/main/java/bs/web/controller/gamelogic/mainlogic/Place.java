package bs.web.controller.gamelogic.mainlogic;

import bs.web.model.entities.Coordinate;
import bs.web.model.entities.Ship;

/**
 * Created by stalk on 05.05.2017.
 */
public class Place {

    public void place(int[][] battlefield, Ship ship, Coordinate coordinate, boolean orient) {
        coordinate = new Coordinate(coordinate.getXx(), coordinate.getYy());
        int newPrownXX = coordinate.getXx();
        int newPrownYY = coordinate.getYy();
        int size = ship.getSize();
        Coordinate prown = ship.getPrown();
        int prownXX = prown.getXx();
        int prownYY = prown.getYy();
        Coordinate stern = ship.getStern();
        int sternXX = stern.getXx();
        int sternYY = stern.getYy();

        /* --------------------------------------------------------------------------------------------------------------------
                                вертикальное расположение
           --------------------------------------------------------------------------------------------------------------------*/
        if (!orient) {

            /*to replace the ship*/
            if (prownXX != -1) {
                for (int y = prownYY; y <= sternYY; y++) {
                    for (int x = prownXX; x <= sternXX; x++) {
                        battlefield[y][x] = 0;
                    }
                }
                prown.setYy(-1);
                prown.setXx(-1);
                stern.setXx(-1);
                stern.setYy(-1);
            }

            if (newPrownYY > 10 - ship.getSize()) {
                return;   //выход за границы массива
            }
            if (battlefield[newPrownYY][newPrownXX] == 2) {
                return; //занятость текущей клетки
            }

            if (newPrownYY - 1 >= 0) {  //граница сверху                               //проверка первого квадрата(носа) на корабли вплотную

                if (battlefield[newPrownYY - 1][newPrownXX] == 2) {
                    return;
                }
                if (newPrownXX - 1 >= 0) { //граница слева (сверху)
                    if (battlefield[newPrownYY - 1][newPrownXX - 1] == 2) {
                        return;
                    }
                }
                if (newPrownXX + 1 <= 9) { //граница справа (сверху)
                    if (battlefield[newPrownYY - 1][newPrownXX + 1] == 2) {
                        return;
                    }
                }
            }
            if (newPrownYY + 1 <= 9) { //граница снизу
                if (battlefield[newPrownYY + 1][newPrownXX] == 2) {
                    return;
                }
            }
            if (newPrownXX - 1 >= 0) { //граница слева
                if (battlefield[newPrownYY][newPrownXX - 1] == 2) {
                    return;
                }
            }
            if (newPrownXX + 1 <= 9) { //граница справа
                if (battlefield[newPrownYY][newPrownXX + 1] == 2) {
                    return;
                }
            }

            for (int i = 1; i < size - 1; i++) {                        //проверка палубы на корабли вплотную
                if (newPrownXX + 1 <= 9) //граница справа
                {
                    if (battlefield[newPrownYY + i][newPrownXX + 1] == 2) {
                        return;
                    }
                }
                if (newPrownXX - 1 >= 0) //граница слева
                {
                    if (battlefield[newPrownYY + i][newPrownXX - 1] == 2) {
                        return;
                    }
                }
                if (newPrownYY + 1 <= 9) //граница снизу
                {
                    if (battlefield[newPrownYY + i + 1][newPrownXX] == 2) {
                        return;
                    }
                }
            }

            if (newPrownYY + 1 <= 9)  // граница снизу                               //проверка последнего квадрата(кормы) на корабли вплотную
            {
                if (battlefield[newPrownYY + (size - 1) + 1][newPrownXX] == 2) {
                    return;
                }

                if (newPrownXX + 1 <= 9) // граница справа(снизу)
                {
                    if (battlefield[newPrownYY + (size - 1) + 1][newPrownXX + 1] == 2) {
                        return;
                    }
                }
                if (newPrownXX - 1 >= 0) // граница слева(снизу)
                {
                    if (battlefield[newPrownYY + (size - 1) + 1][newPrownXX - 1] == 2) {
                        return;
                    }
                }
            }
            if (newPrownXX - 1 >= 0)  //граница слева
            {
                if (battlefield[newPrownYY + (size - 1)][newPrownXX - 1] == 2) {
                    return;
                }
            }
            if (newPrownXX + 1 <= 9)  // граница справа
            {
                if (battlefield[newPrownYY + (size - 1)][newPrownXX + 1] == 2) {
                    return;
                }
            }


            for (int i = 0; i < size; i++) {
                battlefield[newPrownYY + i][newPrownXX] = 2;                              // введение в матрицу
            }
            ship.setPrown(coordinate);                                                   //введение носа и кормы
            sternYY = newPrownYY + size - 1;
            Coordinate B = new Coordinate(coordinate.getXx(), sternYY);
            ship.setStern(B);

        /* --------------------------------------------------------------------------------------------------------------------
                                горизонтальное расположение
           --------------------------------------------------------------------------------------------------------------------*/
        } else {

            if (prownXX != -1) {                                                 //to replace ship
                for (int y = prownYY; y <= sternYY; y++) {
                    for (int x = prownXX; x <= sternXX; x++) {
                        battlefield[y][x] = 0;
                    }
                }
                prown.setYy(-1);
                prown.setXx(-1);
                stern.setXx(-1);
                stern.setYy(-1);
            }

            if (newPrownXX > 10 - ship.getSize()) {   //выход за границы массива
                return;
            }
            if (newPrownXX - 1 >= 0)   // ограничение слева               //проверка первого квадрата на корабли вплотную
            {
                if (battlefield[newPrownYY][newPrownXX - 1] == 2) {
                    return;
                }
                if (newPrownYY - 1 >= 0) //ограничение слева снизу
                {
                    if (battlefield[newPrownYY - 1][newPrownXX - 1] == 2) {
                        return;
                    }
                }
                if (newPrownYY + 1 <= 9) //ограничение слева сверху
                {
                    if (battlefield[newPrownYY + 1][newPrownXX - 1] == 2) {
                        return;
                    }
                }
            }
            if (newPrownXX + 1 <= 9) //ограничение справа
            {
                if (battlefield[newPrownYY][newPrownXX + 1] == 2) {
                    return;
                }
            }
            if (newPrownYY - 1 >= 0) // ограничение сверху
            {
                if (battlefield[newPrownYY - 1][newPrownXX] == 2) {
                    return;
                }
            }
            if (newPrownYY + 1 <= 9) // ограничение снизу
            {
                if (battlefield[newPrownYY + 1][newPrownXX] == 2) {
                    return;
                }
            }

            for (int i = 1; i < size - 1; i++)                      //проверка палубы на корабли вплотную
            {
                if (newPrownYY - 1 >= 0) //ограничение снизу
                {
                    if (battlefield[newPrownYY - 1][newPrownXX + i] == 2) {
                        return;
                    }
                }
                if (newPrownYY + 1 <= 9) //ограничение сверху
                {
                    if (battlefield[newPrownYY + 1][newPrownXX + i] == 2) {
                        return;
                    }
                }
                if (newPrownXX + 1 <= 9) //ограничение справа
                {
                    if (battlefield[newPrownYY][newPrownXX + i + 1] == 2) {
                        return;
                    }
                }

            }

            if (newPrownXX + 1 <= 9)  // ограничение справа                            //проверка последнего квадрата на корабли вплотную
            {
                if (battlefield[newPrownYY][newPrownXX + (size - 1) + 1] == 2) {
                    return;
                }
                if (newPrownYY - 1 >= 0)  //ограничение справа сверху
                {
                    if (battlefield[newPrownYY - 1][newPrownXX + (size - 1) + 1] == 2) {
                        return;
                    }
                }
                if (newPrownYY + 1 <= 9) //ограничение справа снизу
                {
                    if (battlefield[newPrownYY + 1][newPrownXX + (size - 1) + 1] == 2) {
                        return;
                    }
                }
            }
            if (newPrownYY + 1 <= 9)  //ограничение снизу
            {
                if (battlefield[newPrownYY + 1][newPrownXX + (size - 1)] == 2) {
                    return;
                }
            }
            if (newPrownXX - 1 >= 0) // ограничение сверху
            {
                if (battlefield[newPrownYY - 1][newPrownXX + (size - 1)] == 2) {
                    return;
                }
            }


            for (int i = 0; i < size; i++) {
                battlefield[newPrownYY][newPrownXX + i] = 2;                     // введение в матрицу
            }
            ship.setPrown(coordinate);                                           //введение носа и кормы
            sternXX = newPrownXX + size - 1;
            Coordinate B = new Coordinate(sternXX, coordinate.getYy());
            ship.setStern(B);
        }

    }
}
