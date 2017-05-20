package com.web.model.entities;


/**
 * Created by stalk on 23.04.2017.
 */
public class Ship {
    private int size;
    private int dmg;
    private Coordinate prown;
    private Coordinate stern;

    private Ship() {
    }

    ;

    Ship(int size) {
        this.size = size;
        this.dmg = 0;
        this.prown = new Coordinate();
        this.stern = new Coordinate();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Coordinate getPrown() {
        return prown;
    }

    public void setPrown(Coordinate prown) {
        this.prown = prown;
    }

    public Coordinate getStern() {
        return stern;
    }

    public void setStern(Coordinate stern) {
        this.stern = stern;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }
}