package com.devint.cindy.speedmemory.context;

import com.devint.cindy.speedmemory.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeanloicmugnier on 13/05/15.
 */
public class Aleatoire extends MusicStyles {

    static boolean created=false;

    private Aleatoire(List<Integer> songs){

        super(songs);
    }
    public static Aleatoire getAleatoire() {
        if (!created) {
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
            created = true;
            return new Aleatoire(songs);
        }
        return null;
    }
}