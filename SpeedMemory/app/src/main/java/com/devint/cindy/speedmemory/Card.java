package com.devint.cindy.speedmemory;


import android.widget.Button;

/**
 * Created by Cindy Najjar on 10/03/15.
 */

public class Card {

    public Button button;
    private int audioId;
    private int color;
    private int imageId;
    private int id;
    private boolean isColored = false;
    private boolean isFound = false;


    /**
     * Constructeur 1
     * @param id
     * @param audio
     * @param button
     */
    public Card(int id, int audio, Button button) {
        this.id = id;
        this.audioId = audio;
        this.button = button;
        this.isFound = false;
    }

    /**
     * Constructeur 2
     * @param id
     * @param audio
     * @param imageId
     * @param button
     */
    public Card(int id, int audio, int imageId, Button button) {
        this.id = id;
        this.audioId = audio;
        this.button = button;
        this.imageId = imageId;
        this.isFound = false;
    }

    // GETTERS & SETTERS //
    public void setColorAndAudio(int color, int audio) {
        this.color = color;
        this.audioId = audio;
    }

    public void setImgAndAudio(int imageId, int audio) {
        this.imageId = imageId;
        this.audioId = audio;
    }

    public int getColor() { return this.color; }

    public void setColor(int color) { this.color = color; }

    public int getAudioId() { return this.audioId;}

    public int getId() { return this.id; }

    public void setColored(boolean isColored) { this.isColored = isColored; }

    public boolean isColored() { return isColored; }

    public boolean isCardFound() { return this.isFound; }

    public void setIsCardFound(boolean bool) { this.isFound = bool; }

    public Button getButton() { return button; }

    public void setButton(Button button) { this.button = button; }

    public int getImageId() { return this.imageId; }

    public void setImageId(int imageId) { this.imageId = imageId; }

}
