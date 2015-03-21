package com.devint.cindy.speedmemory;


import android.widget.Button;

/**
 * Created by Cindy Najjar on 10/03/15.
 */

public class Card {

    public int x;
    public int y;
    public Button button;
    private String audioName;
    private int id;

    public Card(Button button, int x,int y) {
        this.x = x;
        this.y=y;
        this.button=button;
    }

    public String getAudioName() {
        return this.audioName;
    }
    public int getId() {
        return this.id;
    }

}
