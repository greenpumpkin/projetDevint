package com.devint.cindy.speedmemory;


import android.widget.Button;

/**
 * Created by Cindy Najjar on 10/03/15.
 */

public class Card {

    public Button button;
    private int audioId;
    private int color;
    private int id;
    private boolean isColored = false;
    private boolean isFind = false;


    public Card(int id, int audio, Button button) {
        this.id = id;
        this.audioId = audio;
        this.button = button;
        this.isFind = false;
    }


    public int getColor() {
        return this.color;
    }

    public void setColor(int color) { this.color = color; }

    public int getAudioId() {
        return this.audioId;
    }

    public int getId() {
        return this.id;
    }

    public void setColored(boolean isColored) { this.isColored = isColored; }

    public boolean isColored() { return isColored; }

    public boolean isCardFind() { return this.isFind; }

    public void setIsCardFind(boolean bool) { this.isFind = bool; }

    public void setColorAndAudio(int color, int audio) {
        this.color = color;
        this.audioId = audio;
    }

    public Button getButton() {
        return button;
    }


    public void setButton(Button button) {
        this.button = button;
    }

}
