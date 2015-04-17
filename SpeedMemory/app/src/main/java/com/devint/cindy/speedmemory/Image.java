package com.devint.cindy.speedmemory;

/**
 * Class that describes a card image
 * Created by Cindy on 03/04/15.
 */
public class Image {


    private int image;
    private int rest = 2;

    /**
     * Constructor
     * @param image
     */
    public Image(int image) {
        this.image = image;
    }

    //GETTERS & SETTERS//
    public int getRest() { return this.rest; }
    public void setRest(int rest) {
        this.rest = rest;
    }
    public int getImage() { return this.image; }
}