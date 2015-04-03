package com.devint.cindy.speedmemory;

/**
 * Created by Cindy on 03/04/15.
 */
public class Image {

    private int image;
    private int reste = 2;
    public Image(int image) {
        this.image = image;
    }
    public int getReste() { return this.reste; }
    public void setReste(int reste) {
        this.reste = reste;
    }
    public int getImage() { return this.image; }
}
