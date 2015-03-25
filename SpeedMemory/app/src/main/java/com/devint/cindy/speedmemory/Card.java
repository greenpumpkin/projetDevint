package com.devint.cindy.speedmemory;


import android.widget.Button;

/**
 * Created by Cindy Najjar on 10/03/15.
 */

public class Card {

    public Button button;
    private String audioName;
    private int color;
    private int id;


    public Card(Button button, int id, int color) {
        this.button=button;
        this.color = color;
        this.id = id;
    }

    public Card(Button button, int id, int color, String audioName) {
        this.button=button;
        this.color = color;
        this.id = id;
        this.audioName = audioName;
    }

    public int getColor() {
        return this.color;
    }

    public String getAudioName() {
        return this.audioName;
    }
    public int getId() {
        return this.id;
    }

}
