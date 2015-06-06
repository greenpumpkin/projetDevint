package com.devint.cindy.speedmemory.context;

import com.devint.cindy.speedmemory.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeanloicmugnier on 10/05/15.
 */
public class Pop extends MusicStyles {

    static boolean created=false;

    private Pop(List<Integer>songs){

        super(songs);
    }
    public static Pop getPop(){
        //if(!created){
            List<Integer> songs = new ArrayList<Integer>();
            songs.add(R.raw.blankspacetaylor);
            songs.add(R.raw.cantremmembershakira);
            songs.add(R.raw.drunkinlovebeyonce);
            songs.add(R.raw.loyalchris);
            songs.add(R.raw.mirrorsjustin);
            songs.add(R.raw.problemariana);
            songs.add(R.raw.rudemagic);
            songs.add(R.raw.selfiechain);
            songs.add(R.raw.shakeitoffcover);
            songs.add(R.raw.staywithmesam);
            songs.add(R.raw.summercalvin);
            songs.add(R.raw.talkdirtyjason);
            songs.add(R.raw.wavezprobz);
            created=true;
            return new Pop(songs);
        //}
        //return null;
    }


}
