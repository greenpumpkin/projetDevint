package com.devint.cindy.speedmemory.context;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.devint.cindy.speedmemory.context.Card;

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
