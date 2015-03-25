package com.devint.cindy.speedmemory;

/**
 * Created by Aina et Cindy on 25/03/15.
 */

public class Couleur {

    private int color;
    private int reste = 2;

    public Couleur(int color) {
        this.color = color;
    }

    public int getReste() { return this.reste; }
    public void setReste(int reste) {
        this.reste = reste;
    }

    public int getColor() { return this.color; }
}
