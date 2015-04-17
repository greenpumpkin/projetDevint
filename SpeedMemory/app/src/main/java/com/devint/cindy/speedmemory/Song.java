package com.devint.cindy.speedmemory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Cette classe regroupe toutes les chansons qui seront utilisés dans le projet
 *TODO creer une base de donner xml ou autre pour les morceaux
 *
 * Created by jeanloicmugnier on 30/03/15.
 */
public class Song {
    private List<Integer> listeAudio ;
    private List<Integer> tmpAudio ; // une fois o morceau choisi, on enlève de la liste
    private Random rnd;

    public Song(){
        rnd = new Random();
        listeAudio = new ArrayList<Integer>();
        tmpAudio = new ArrayList<Integer>();
        setListeAudio();
    }

    /**
     * SetListeAudio ajout les int correspondant aux id des fichier audios
     * On duplique listAudio dans tmpAudio
     */
    public void setListeAudio(){

        listeAudio.add(R.raw.morceau1);
        listeAudio.add(R.raw.morceau2);
        listeAudio.add(R.raw.morceau3);
        tmpAudio=listeAudio;
    }

    /**
     *
     *
     * @return int, songId sous la forme R.raw.nomDuFichier
     */
    public int getNextRandomSong(){
        int nb = rnd.nextInt(listeAudio.size());
        int audioId = tmpAudio.get(nb);
        tmpAudio.remove(nb);
        return audioId;
    }
}