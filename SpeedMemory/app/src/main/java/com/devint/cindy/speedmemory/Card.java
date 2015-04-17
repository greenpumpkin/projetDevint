package com.devint.cindy.speedmemory;


import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Describes a card.
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
     * Constructor 1
     * @param id
     * @param audio
     * @param button
     */
    public Card(int id, int audio, Button button) {
        this.id = id;
        this.audioId = audio;
        this.button = button;
    }

    /**
     * Constructor 2
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
    }

    /**
     * Cards are mixed randomly at each game beggining
     * @param map
     * @param r
     */
    public static void mixCards(HashMap<Integer,Card> map, Random r) {

        /**
         * Initialisation images et sons des cartes.
         */
        Image img1 = new Image(R.drawable.sugar_maroon5);
        Image img2 = new Image(R.drawable.bailand_enrique_iglesias);
        Image img3 = new Image(R.drawable.born_in_babylon);
        int son1 = R.raw.morceau1;
        int son2 = R.raw.morceau2;
        int son3 = R.raw.morceau3;

        /**
         * Everything stocked in HashMaps
         */
        HashMap<Image, Integer> imgAndSndList = new HashMap<>();
        imgAndSndList.put(img1, son1);
        imgAndSndList.put(img2, son2);
        imgAndSndList.put(img3, son3);

        ArrayList<Image> list = new ArrayList<Image>();
        list.add(img1);
        list.add(img2);
        list.add(img3);

        /**
         * Random distribution
         */
        for (Card carte : map.values()) {

            int indiceRandom = r.nextInt(list.size());
            Image img = list.get(indiceRandom);
            int idAudio = imgAndSndList.get(img);
            int reste = img.getRest();
            img.setRest(--reste);

            if (img.getRest() == 0) {
                list.remove(indiceRandom);
            }

            carte.setImgAndAudio(img.getImage(), idAudio);

        }
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