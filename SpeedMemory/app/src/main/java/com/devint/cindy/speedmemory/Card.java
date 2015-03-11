package com.devint.cindy.speedmemory;


/**
 * Created by Cindy Najjar on 10/03/15.
 */

public class Card {

    private String uri;
    private int position;
    private String color;
    private int id;
    private boolean isReturned = false;

    public Card(int position,int id) {
        this.position = position;
        this.id = id;
    }


}
