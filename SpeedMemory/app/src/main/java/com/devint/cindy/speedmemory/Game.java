package com.devint.cindy.speedmemory;

import java.util.ArrayList;
import java.util.List;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.content.Context;
import android.net.Uri;

/**
 * Created by jeanloicmugnier on 10/03/15.
 */
public class Game {

    List<Card> cards;

    public Game(){
        cards = new ArrayList<Card>();
    }
    public void playSound(Card card, Context context){
        /*Uri myUri = Uri.parse("../../../../../res/raw" + card.getAudioName()); // initialize Uri here

        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(context, myUri);
            mediaPlayer.prepare();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        mediaPlayer.start();*/
        MediaPlayer mediaPlayer = MediaPlayer.create(context, card.getId());
        mediaPlayer.start();


    }

    public Card getCard(int num){
        return cards.get(num);
    }
}
