package com.devint.cindy.speedmemory;

/**
 * Created by Cindy Najjar on 10/03/15.
 */

public class Card {

    private int position;
    private String color;
    private boolean isReturned = false;
    //+ attribut pour le son

    public Card(int position,String color) {
        this.position = position;
        this.color = color;
    }


}
