package bs.web.model.entities.preload;

import bs.web.model.entities.Battle;
import bs.web.model.entities.Battlefield;
import bs.web.model.entities.Coordinate;
import bs.web.model.entities.Ship;
import bs.web.util.Convert;

import java.util.ArrayList;

/**
 * Created by stalk on 01.05.2017.
 */
public class WhyNot {

    public static Battle battle = new Battle();
    public static ArrayList battlefields = battle.getBattlefields();

    public static Battlefield player1battlefieldclass = (Battlefield) battlefields.get(0);
    public static Battlefield player2battlefieldclass = (Battlefield) battlefields.get(1);

    public static ArrayList<Ship> player1fleet = player1battlefieldclass.getFleet();
    public static ArrayList<Ship> player2fleet = player2battlefieldclass.getFleet();

    public static int[][] player1battlefield = player1battlefieldclass.getBattlefield();
    public static int[][] player2battlefield = player2battlefieldclass.getBattlefield();

    public static Ship player1four1 = player1fleet.get(0);
    public static Ship player1three1 = player1fleet.get(1);
    public static Ship player1three2 = player1fleet.get(2);
    public static Ship player1two1 = player1fleet.get(3);
    public static Ship player1two2 = player1fleet.get(4);
    public static Ship player1two3 = player1fleet.get(5);
    public static Ship player1one1 = player1fleet.get(6);
    public static Ship player1one2 = player1fleet.get(7);
    public static Ship player1one3 = player1fleet.get(8);
    public static Ship player1one4 = player1fleet.get(9);

    public static Ship player2four1 = player2fleet.get(0);
    public static Ship player2three1 = player2fleet.get(1);
    public static Ship player2three2 = player2fleet.get(2);
    public static Ship player2two1 = player2fleet.get(3);
    public static Ship player2two2 = player2fleet.get(4);
    public static Ship player2two3 = player2fleet.get(5);
    public static Ship player2one1 = player2fleet.get(6);
    public static Ship player2one2 = player2fleet.get(7);
    public static Ship player2one3 = player2fleet.get(8);
    public static Ship player2one4 = player2fleet.get(9);

    public static boolean horizontal = true;
    public static boolean vertical = false;

    public static Coordinate A1 = Convert.convert('a', 1);
    public static Coordinate A2 = Convert.convert('a', 2);
    public static Coordinate A3 = Convert.convert('a', 3);
    public static Coordinate A4 = Convert.convert('a', 4);
    public static Coordinate A5 = Convert.convert('a', 5);
    public static Coordinate A6 = Convert.convert('a', 6);
    public static Coordinate A7 = Convert.convert('a', 7);
    public static Coordinate A8 = Convert.convert('a', 8);
    public static Coordinate A9 = Convert.convert('a', 9);
    public static Coordinate A10 = Convert.convert('a', 10);

    public static Coordinate B1 = Convert.convert('b', 1);
    public static Coordinate B2 = Convert.convert('b', 2);
    public static Coordinate B3 = Convert.convert('b', 3);
    public static Coordinate B4 = Convert.convert('b', 4);
    public static Coordinate B5 = Convert.convert('b', 5);
    public static Coordinate B6 = Convert.convert('b', 6);
    public static Coordinate B7 = Convert.convert('b', 7);
    public static Coordinate B8 = Convert.convert('b', 8);
    public static Coordinate B9 = Convert.convert('b', 9);
    public static Coordinate B10 = Convert.convert('b', 10);

    public static Coordinate C1 = Convert.convert('c', 1);
    public static Coordinate C2 = Convert.convert('c', 2);
    public static Coordinate C3 = Convert.convert('c', 3);
    public static Coordinate C4 = Convert.convert('c', 4);
    public static Coordinate C5 = Convert.convert('c', 5);
    public static Coordinate C6 = Convert.convert('c', 6);
    public static Coordinate C7 = Convert.convert('c', 7);
    public static Coordinate C8 = Convert.convert('c', 8);
    public static Coordinate C9 = Convert.convert('c', 9);
    public static Coordinate C10 = Convert.convert('c', 10);

    public static Coordinate D1 = Convert.convert('d', 1);
    public static Coordinate D2 = Convert.convert('d', 2);
    public static Coordinate D3 = Convert.convert('d', 3);
    public static Coordinate D4 = Convert.convert('d', 4);
    public static Coordinate D5 = Convert.convert('d', 5);
    public static Coordinate D6 = Convert.convert('d', 6);
    public static Coordinate D7 = Convert.convert('d', 7);
    public static Coordinate D8 = Convert.convert('d', 8);
    public static Coordinate D9 = Convert.convert('d', 9);
    public static Coordinate D10 = Convert.convert('d', 10);

    public static Coordinate E1 = Convert.convert('e', 1);
    public static Coordinate E2 = Convert.convert('e', 2);
    public static Coordinate E3 = Convert.convert('e', 3);
    public static Coordinate E4 = Convert.convert('e', 4);
    public static Coordinate E5 = Convert.convert('e', 5);
    public static Coordinate E6 = Convert.convert('e', 6);
    public static Coordinate E7 = Convert.convert('e', 7);
    public static Coordinate E8 = Convert.convert('e', 8);
    public static Coordinate E9 = Convert.convert('e', 9);
    public static Coordinate E10 = Convert.convert('e', 10);

    public static Coordinate F1 = Convert.convert('f', 1);
    public static Coordinate F2 = Convert.convert('f', 2);
    public static Coordinate F3 = Convert.convert('f', 3);
    public static Coordinate F4 = Convert.convert('f', 4);
    public static Coordinate F5 = Convert.convert('f', 5);
    public static Coordinate F6 = Convert.convert('f', 6);
    public static Coordinate F7 = Convert.convert('f', 7);
    public static Coordinate F8 = Convert.convert('f', 8);
    public static Coordinate F9 = Convert.convert('f', 9);
    public static Coordinate F10 = Convert.convert('f', 10);

    public static Coordinate G1 = Convert.convert('g', 1);
    public static Coordinate G2 = Convert.convert('g', 2);
    public static Coordinate G3 = Convert.convert('g', 3);
    public static Coordinate G4 = Convert.convert('g', 4);
    public static Coordinate G5 = Convert.convert('g', 5);
    public static Coordinate G6 = Convert.convert('g', 6);
    public static Coordinate G7 = Convert.convert('g', 7);
    public static Coordinate G8 = Convert.convert('g', 8);
    public static Coordinate G9 = Convert.convert('g', 9);
    public static Coordinate G10 = Convert.convert('g', 10);

    public static Coordinate H1 = Convert.convert('h', 1);
    public static Coordinate H2 = Convert.convert('h', 2);
    public static Coordinate H3 = Convert.convert('h', 3);
    public static Coordinate H4 = Convert.convert('h', 4);
    public static Coordinate H5 = Convert.convert('h', 5);
    public static Coordinate H6 = Convert.convert('h', 6);
    public static Coordinate H7 = Convert.convert('h', 7);
    public static Coordinate H8 = Convert.convert('h', 8);
    public static Coordinate H9 = Convert.convert('h', 9);
    public static Coordinate H10 = Convert.convert('h', 10);

    public static Coordinate I1 = Convert.convert('i', 1);
    public static Coordinate I2 = Convert.convert('i', 2);
    public static Coordinate I3 = Convert.convert('i', 3);
    public static Coordinate I4 = Convert.convert('i', 4);
    public static Coordinate I5 = Convert.convert('i', 5);
    public static Coordinate I6 = Convert.convert('i', 6);
    public static Coordinate I7 = Convert.convert('i', 7);
    public static Coordinate I8 = Convert.convert('i', 8);
    public static Coordinate I9 = Convert.convert('i', 9);
    public static Coordinate I10 = Convert.convert('i', 10);

    public static Coordinate J1 = Convert.convert('j', 1);
    public static Coordinate J2 = Convert.convert('j', 2);
    public static Coordinate J3 = Convert.convert('j', 3);
    public static Coordinate J4 = Convert.convert('j', 4);
    public static Coordinate J5 = Convert.convert('j', 5);
    public static Coordinate J6 = Convert.convert('j', 6);
    public static Coordinate J7 = Convert.convert('j', 7);
    public static Coordinate J8 = Convert.convert('j', 8);
    public static Coordinate J9 = Convert.convert('j', 9);
    public static Coordinate J10 = Convert.convert('j', 10);
}
