package com.devint.cindy.speedmemory.context;


import android.widget.Button;

import com.devint.cindy.speedmemory.R;
import com.devint.cindy.speedmemory.activities.MainActivity;

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
    public static void mixCardsEasy(HashMap<Integer,Card> map, Random r) {

       /* int son1 = R.raw.morceau1;
        int son2 = R.raw.morceau2;
        int son3 = R.raw.morceau3;*/

        String style = MainActivity.settings.getString("styleKey", "Rap");
        MusicStyles musicStyles = MusicStyles.getStyle(style);

        int son1 = musicStyles.getNextRandomSong();
        int son2 = musicStyles.getNextRandomSong();
        int son3 = musicStyles.getNextRandomSong();


        /**
         * Initialisation images et sons des cartes.
         */
        Image img1 = new Image(getImage(son1));
        Image img2 = new Image(getImage(son2));
        Image img3 = new Image(getImage(son3));

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

    public static void mixCardsMedium(HashMap<Integer,Card> map, Random r) {

       /* int son1 = R.raw.morceau1;
        int son2 = R.raw.morceau2;
        int son3 = R.raw.morceau3;
        int son4 = R.raw.eminemberzerk;*/

        String style = MainActivity.settings.getString("styleKey", "Rap");
        MusicStyles musicStyles = MusicStyles.getStyle(style);

        int son1 = musicStyles.getNextRandomSong();
        int son2 = musicStyles.getNextRandomSong();
        int son3 = musicStyles.getNextRandomSong();
        int son4 = musicStyles.getNextRandomSong();

        /**
         * Initialisation images et sons des cartes.
         */
        Image img1 = new Image(getImage(son1));
        Image img2 = new Image(getImage(son2));
        Image img3 = new Image(getImage(son3));
        Image img4 = new Image(getImage(son4));

        /**
         * Everything stocked in HashMaps
         */
        HashMap<Image, Integer> imgAndSndList = new HashMap<>();
        imgAndSndList.put(img1, son1);
        imgAndSndList.put(img2, son2);
        imgAndSndList.put(img3, son3);
        imgAndSndList.put(img4, son4);

        ArrayList<Image> list = new ArrayList<Image>();
        list.add(img1);
        list.add(img2);
        list.add(img3);
        list.add(img4);

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

    /**
     * Cards are mixed randomly at each game beggining
     * @param map
     * @param r
     */
    public static void mixCardsHard(HashMap<Integer,Card> map, Random r) {

       /* int son1 = R.raw.morceau1;
        int son2 = R.raw.morceau2;
        int son3 = R.raw.morceau3;
        int son4 = R.raw.eminemberzerk;
        int son5 = R.raw.chrisbrownshowme;*/

        String style = MainActivity.settings.getString("styleKey", "Rap");
        MusicStyles musicStyles = MusicStyles.getStyle(style);

        int son1 = musicStyles.getNextRandomSong();
        int son2 = musicStyles.getNextRandomSong();
        int son3 = musicStyles.getNextRandomSong();
        int son4 = musicStyles.getNextRandomSong();
        int son5 = musicStyles.getNextRandomSong();

        /**
         * Initialisation images et sons des cartes.
         */
        Image img1 = new Image(getImage(son1));
        Image img2 = new Image(getImage(son2));
        Image img3 = new Image(getImage(son3));
        Image img4 = new Image(getImage(son4));
        Image img5 = new Image(getImage(son5));

        /**
         * Everything stocked in HashMaps
         */
        HashMap<Image, Integer> imgAndSndList = new HashMap<>();
        imgAndSndList.put(img1, son1);
        imgAndSndList.put(img2, son2);
        imgAndSndList.put(img3, son3);
        imgAndSndList.put(img4, son4);
        imgAndSndList.put(img5, son5);

        ArrayList<Image> list = new ArrayList<Image>();
        list.add(img1);
        list.add(img2);
        list.add(img3);
        list.add(img4);
        list.add(img5);

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

    public static int getImage(int idSon) {
        switch (idSon) {
            case R.raw.animalsgarrix : return R.drawable.animalsgarrix;
            case R.raw.badmanauto : return R.drawable.badmanauto;
            case R.raw.blankspacetaylor : return R.drawable.blankspacetaylor;
            case R.raw.cantremmembershakira : return R.drawable.cantremmembershakira;
            case R.raw.cheerupnewworld : return R.drawable.cheerupnewworld;
            case R.raw.chrisbrownshowme : return R.drawable.chrisbrownshowme;
            case R.raw.darkwarriorandrew : return R.drawable.darkwarriorandrew;
            case R.raw.dischargecurbi : return R.drawable.dischargecurbi;
            case R.raw.drakeallme : return R.drawable.drakeallme;
            case R.raw.drunkinlovebeyonce : return R.drawable.drunkinlovebeyonce;
            case R.raw.eclipsehard : return R.drawable.eclipsehard;
            case R.raw.eminemberzerk : return R.drawable.eminemberzerk;
            case R.raw.eminemrapgod : return R.drawable.eminemrapgod;
            case R.raw.eminemsurvival : return R.drawable.eminemsurvival;
            case R.raw.feetonthegroundnicky : return R.drawable.feetonthegroundnicky;
            case R.raw.fergilalove : return R.drawable.fergilalove;
            case R.raw.followmehard : return R.drawable.followmehard;
            case R.raw.foreverblaster : return R.drawable.foreverblaster;
            case R.raw.goldskiestiesto : return R.drawable.goldskiestiesto;
            case R.raw.howyouloveme3lau : return R.drawable.howyouloveme3lau;
            case R.raw.iggyazaleablackwidow : return R.drawable.iggyazaleablackwidow;
            case R.raw.iggyfancy : return R.drawable.iggyfancy;
            case R.raw.lilwaynmynigga : return R.drawable.lilwaynmynigga;
            case R.raw.loyalchris : return R.drawable.loyalchris;
            case R.raw.martingarrixamf2014 : return R.drawable.martingarrixamf2014;
            case R.raw.migosfightnight : return R.drawable.migosfightnight;
            case R.raw.mirrorsjustin : return R.drawable.mirrorsjustin;
            case R.raw.problemariana : return R.drawable.problemariana;
            case R.raw.raenoflexzone : return R.drawable.raenoflexzone;
            case R.raw.raenotype : return R.drawable.raenotype;
            case R.raw.rudemagic : return R.drawable.rudemagic;
            case R.raw.secretstiesto : return R.drawable.secretstiesto;
            case R.raw.selfiechain : return R.drawable.selfiechain;
            case R.raw.shakeitoffcover : return R.drawable.shakeitoffcover;
            case R.raw.sopranofreshprince : return R.drawable.sopranofreshprince;
            case R.raw.spacemanhard : return R.drawable.spacemanhard;
            case R.raw.staywithmesam : return R.drawable.staywithmesam;
            case R.raw.summercalvin : return R.drawable.summercalvin;
            case R.raw.talkdirtyjason : return R.drawable.talkdirtyjason;
            case R.raw.wavezprobz : return R.drawable.wavezprobz;
            case R.raw.weliketopartyshow : return R.drawable.weliketopartyshow;
            case R.raw.wigglejason : return R.drawable.wigglejason;
            case R.raw.wizkhalifawedemboys : return R.drawable.wizkhalifawedemboys;
            case R.raw.yeedeorro : return R.drawable.yeedeorro;
            case R.raw.ymdraketrophies : return R.drawable.ymdraketrophies;
        }
        return 0;
    }

}
