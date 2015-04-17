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




    }

    public Card getCard(int num){
        return cards.get(num);
    }
}