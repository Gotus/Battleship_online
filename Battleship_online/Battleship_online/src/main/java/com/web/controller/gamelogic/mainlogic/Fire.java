package edu.web.controller.gamelogic.mainlogic;

import edu.web.model.entities.Battlefield;
import edu.web.model.entities.Coordinate;
import edu.web.model.entities.Ship;

import java.util.ArrayList;

/**
 * Created by stalk on 05.05.2017.
 */
public class Fire {

    public int fire(Battlefield battlefieldclass, Coordinate shot) { //функция проверяет есть ли корабль в клетке и ставит 3 или 1 + deadzone, если корабль мертв
        int[][] battlefield = battlefieldclass.getBattlefield();
        ArrayList<Ship> fleet = battlefieldclass.getFleet();

        boolean hitcheck = false; //для выхода из циклов

        int shotYY = shot.getYy();
        int shotXX = shot.getXx();

        if (battlefield[shotYY][shotXX] == 2) {
            for (Ship ship : fleet) {
                Coordinate prown = ship.getPrown();
                Coordinate stern = ship.getStern();

                for (int x = prown.getXx(); x <= stern.getXx(); x++) {
                    for (int y = prown.getYy(); y <= stern.getYy(); y++) {
                        if ((x == shotXX) && (y == shotYY)) {
                            ship.setDmg(ship.getDmg() + 1);
                            battlefield[shotYY][shotXX] = 3;
                            hitcheck = true;
                            break;
                        }
                    }
                    if (hitcheck) {
                        break;
                    }
                }
            }

            for (int i = 0; i < fleet.size(); i++) {
                Ship ship = fleet.get(i);

                if (ship.getSize() == ship.getDmg()) // вызов deadzone, если корабль убит
                {
                    deadzone(battlefield, ship);
                    fleet.remove(i);
                    return 3;
                }
            }

            return 2;
        } else {
            battlefield[shotYY][shotXX] = 1;
            return 1;
        }


    }

    private void deadzone(int[][] battlefield, Ship ship) {
        Coordinate prown = ship.getPrown();
        Coordinate stern = ship.getStern();

        for (int x = prown.getXx(); x <= stern.getXx(); x++) {
            for (int y = prown.getYy(); y <= stern.getYy(); y++) {
                battlefield[y][x] = 3;
                if (prown.getXx() == stern.getXx()) // вертикальное расположение deadzone палубы
                {
                    if (x + 1 <= 9) battlefield[y][x + 1] = 1;
                    if (x - 1 >= 0) battlefield[y][x - 1] = 1;
                } else //горизонтальное расположение deadzone палубы
                {
                    if (y - 1 >= 0) battlefield[y - 1][x] = 1;
                    if (y + 1 <= 9) battlefield[y + 1][x] = 1;
                }
            }
        }
        /*deadzone около кормы и носа*/
        if (prown.getXx() == stern.getXx()) {

            /*вертикальное расположение*/
            if (prown.getYy() - 1 >= 0) {
                battlefield[prown.getYy() - 1][prown.getXx()] = 1;
                if (prown.getXx() - 1 >= 0) {
                    battlefield[prown.getYy() - 1][prown.getXx() - 1] = 1;
                }
                if (prown.getXx() + 1 <= 9) {
                    battlefield[prown.getYy() - 1][prown.getXx() + 1] = 1;
                }
            }

            if (stern.getYy() + 1 <= 9) {
                battlefield[stern.getYy() + 1][stern.getXx()] = 1;
                if (stern.getXx() - 1 >= 0) {
                    battlefield[stern.getYy() + 1][stern.getXx() - 1] = 1;
                }
                if (stern.getXx() + 1 <= 9) {
                    battlefield[stern.getYy() + 1][stern.getXx() + 1] = 1;
                }
            }
        } else {

            /*горизонтальное расположение*/
            if (prown.getXx() - 1 >= 0) {
                battlefield[prown.getYy()][prown.getXx() - 1] = 1;
                if (prown.getYy() - 1 >= 0) {
                    battlefield[prown.getYy() - 1][prown.getXx() - 1] = 1;
                }
                if (prown.getYy() + 1 <= 9) {
                    battlefield[prown.getYy() + 1][prown.getXx() - 1] = 1;
                }
            }

            if (stern.getXx() + 1 <= 9) {
                battlefield[stern.getYy()][stern.getXx() + 1] = 1;
                if (prown.getYy() - 1 >= 0) {
                    battlefield[stern.getYy() - 1][stern.getXx() + 1] = 1;
                }
                if (prown.getYy() + 1 <= 9) {
                    battlefield[stern.getYy() + 1][stern.getXx() + 1] = 1;
                }
            }
        }

    }
}
