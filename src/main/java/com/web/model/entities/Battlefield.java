package com.web.model.entities;

import java.util.ArrayList;

/**
 * Created by stalk on 23.04.2017.
 */
public class Battlefield {
    private int[][] battlefield;
    private ArrayList<Ship> fleet;

    Battlefield() {
        battlefield = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                battlefield[i][j] = 0;
            }
        }

        fleet = new ArrayList<Ship>();

        Ship four1 = new Ship(4);
        Ship three1 = new Ship(3);
        Ship three2 = new Ship(3);
        Ship two1 = new Ship(2);
        Ship two2 = new Ship(2);
        Ship two3 = new Ship(2);
        Ship one1 = new Ship(1);
        Ship one2 = new Ship(1);
        Ship one3 = new Ship(1);
        Ship one4 = new Ship(1);

        fleet.add(four1);//0
        fleet.add(three1);//1
        fleet.add(three2);//2
        fleet.add(two1);//3
        fleet.add(two2);//4
        fleet.add(two3);//5
        fleet.add(one1);//6
        fleet.add(one2);//7
        fleet.add(one3);//8
        fleet.add(one4);//9

    }

    public int[][] getBattlefield() {
        return battlefield;
    }

    public void setBattlefield(int[][] battlefield) {
        this.battlefield = battlefield;
    }

    public ArrayList<Ship> getFleet() {
        return fleet;
    }

    public void setFleet(ArrayList<Ship> fleet) {
        this.fleet = fleet;
    }
}
