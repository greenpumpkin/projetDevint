package com.devint.cindy.speedmemory.context;

import com.devint.cindy.speedmemory.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeanloicmugnier on 05/05/15.
 */
public class Rap extends MusicStyles {

    static boolean created=false;

    private Rap(List<Integer> songs){

        super(songs);
    }
    public static Rap getRap(){
        //if(!created){
            List<Integer> songs = new ArrayList<Integer>();
            songs.add(R.raw.chrisbrownshowme);
            songs.add(R.raw.drakeallme);
            songs.add(R.raw.eminemsurvival);
            songs.add(R.raw.eminemrapgod);
            songs.add(R.raw.eminemberzerk);
            songs.add(R.raw.fergilalove);
            songs.add(R.raw.iggyfancy);
            songs.add(R.raw.iggyazaleablackwidow);
            songs.add(R.raw.lilwaynmynigga);
            songs.add(R.raw.migosfightnight);
            songs.add(R.raw.raenoflexzone);
            songs.add(R.raw.raenotype);
            songs.add(R.raw.sopranofreshprince);
            songs.add(R.raw.ymdraketrophies);
            created=true;
            return new Rap(songs);
        //}
        //return null;
    }
}