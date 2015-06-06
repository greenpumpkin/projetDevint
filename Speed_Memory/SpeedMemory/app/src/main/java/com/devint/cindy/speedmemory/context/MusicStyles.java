package com.devint.cindy.speedmemory.context;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by jeanloicmugnier on 05/05/15.
 */
public abstract class MusicStyles {

    List<Integer> songs;
    private Random rnd;
    List<Integer> tmpAudio;

    public MusicStyles(List<Integer> songs){
        this.songs = songs;
        tmpAudio = new ArrayList<>();
        rnd = new Random();
        reset();
    }

    public void reset(){
        this.tmpAudio = songs;
    }

    /**
     *
     *
     * @return int, songId sous la forme R.raw.nomDuFichier
     */
    public int getNextRandomSong(){
        tmpAudio = songs;
        int nb = rnd.nextInt(songs.size());
        int audioId = tmpAudio.get(nb);
        tmpAudio.remove(nb);
        return audioId;
    }

    public static MusicStyles getStyle(String name){
        if("Rap".equals(name))          return Rap.getRap();
        else if("Pop".equals(name))     return Pop.getPop();
        else if ("Electro".equals(name))return Electro.getElectro();
        else if ("Aleatoire".equals(name))return Aleatoire.getAleatoire();
        else return Rap.getRap();
    }
}