package com.devint.cindy.speedmemory;


/**
 * Created by Cindy Najjar on 10/03/15.
 */

public class Card {

    private String audioName;
    private String color;
    private int id;
    private boolean isReturned;

    public Card(int id,String audioName) {
        this.id = id;
        this.audioName = audioName;
        isReturned = false;
    }

    public String getAudioName() {
        return this.audioName;
    }
    public int getId() {
        return this.id;
    }

}
