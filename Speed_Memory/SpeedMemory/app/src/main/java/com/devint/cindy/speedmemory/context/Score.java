package com.devint.cindy.speedmemory.context;

import java.util.Comparator;

/**
 * Created by Aina on 17/04/15.
 */
public class Score implements Comparable<Score>{


    private int id;
    private String pseudo;
    private int score;
    private int time;
    private int niveau;

    public Score() {
        this.pseudo = "unknown";
        this.score = 0;
        this.time = 0;
        this.niveau = 1;
    }

    public Score(String pseudo, int score, int time) {
        this.pseudo = pseudo;
        this.score = score;
        this.time = time;
        this.niveau = 1;
    }

    public Score(int niveau, String pseudo, int score, int time) {
        this.pseudo = pseudo;
        this.score = score;
        this.time = time;
        this.niveau = niveau;
    }

    public Score(String pseudo, int score) {
        this.pseudo = pseudo;
        this.score = score;
        this.time = 0;
        this.niveau = 1;
    }

    public String getPseudo() {
        return pseudo;
    }

    public int getTime() {
        return time;
    }

    public int getScore() {
        return score;
    }

    public int getId() {
        return id;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    @Override
    public String toString() {
        return pseudo + " : " + score;
    }

    public int compareTo(Score score) {
        return (this.score < score.getScore() ) ? -1: (this.score > score.getScore()) ? 1:0 ;

    }
}
