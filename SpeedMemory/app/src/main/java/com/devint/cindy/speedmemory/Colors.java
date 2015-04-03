package com.devint.cindy.speedmemory;

/**
 * Created by Aina et Cindy on 25/03/15.
 */

public class Colors {

    private int color;
    private int rest = 2;

    public Colors(int color) {
        this.color = color;
    }

    public int getRest() { return this.rest; }
    public void setRest(int rest) {
        this.rest = rest;
    }

    public int getColor() { return this.color; }
}
