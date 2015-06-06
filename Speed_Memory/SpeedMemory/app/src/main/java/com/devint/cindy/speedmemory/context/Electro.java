package com.devint.cindy.speedmemory.context;

import com.devint.cindy.speedmemory.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeanloicmugnier on 11/05/15.
 */
public class Electro extends MusicStyles {

    static boolean created=false;

    private Electro(List<Integer> songs){

        super(songs);
    }

    public static Electro getElectro(){
        //if(!created){
            List<Integer> songs = new ArrayList<Integer>();
            songs.add(R.raw.animalsgarrix);
            songs.add(R.raw.badmanauto);
            songs.add(R.raw.cheerupnewworld);
            songs.add(R.raw.darkwarriorandrew);
            songs.add(R.raw.dischargecurbi);
            songs.add(R.raw.eclipsehard);
            songs.add(R.raw.feetonthegroundnicky);
            songs.add(R.raw.followmehard);
            songs.add(R.raw.foreverblaster);
            songs.add(R.raw.goldskiestiesto);
            songs.add(R.raw.howyouloveme3lau);
            songs.add(R.raw.secretstiesto);
            songs.add(R.raw.spacemanhard);
            songs.add(R.raw.weliketopartyshow);
            songs.add(R.raw.yeedeorro);
            created=true;
            return new Electro(songs);
        //}
        //return null;
    }
}
