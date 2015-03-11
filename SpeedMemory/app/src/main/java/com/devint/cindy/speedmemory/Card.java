package com.devint.cindy.speedmemory;


/**
 * Created by Cindy Najjar on 10/03/15.
 */

public class Card {

    private String url;
    private String color;
    private int id;
    private boolean isReturned;

    public Card(int id,String url) {
        this.id = id;
        this.url = url;
        isReturned = false;
    }

    public String getUrl() {
        return this.url;
    }


}
