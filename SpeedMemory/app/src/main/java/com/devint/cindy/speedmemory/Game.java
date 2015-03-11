package com.devint.cindy.speedmemory;

import java.util.ArrayList;
import java.util.List;

import android.media.AsyncPlayer;
import android.content.Context;
import android.net.Uri;

/**
 * Created by jeanloicmugnier on 10/03/15.
 */
public class Game {

    AsyncPlayer ap;
    List<Card> cards;

    public Game(){
        ap = new AsyncPlayer("player");
        cards = new ArrayList<Card>();
    }
    public void playSound(Card card, Context context){
        Uri path = Uri.parse(card.getUrl());
        //ap.play(context,path,false,);//TODO
    }

    public Card getCard(int num){
        return cards.get(num);
    }
}
