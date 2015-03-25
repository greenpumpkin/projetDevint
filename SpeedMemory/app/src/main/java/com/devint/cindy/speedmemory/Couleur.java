package com.devint.cindy.speedmemory;

/**
 * Created by Aina et Cindy on 25/03/15.
 */

public class Couleur {

    private int color;
    private int reste = 2;

    public Couleur(int color, int reste) {
        this.color = color;
        this.reste = reste;
    }

    public void setReste(int reste) {
        this.reste = reste;
    }

}
