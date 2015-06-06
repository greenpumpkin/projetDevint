package com.devint.cindy.speedmemory.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.devint.cindy.speedmemory.context.Score;
import com.devint.cindy.speedmemory.database.DataBaseHandler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Aina on 17/04/15.
 */
public class ScoreDAOSQLite {

    private static final String TABLE_SCORES = "scores";
    private static final String COL_ID = "_id";
    private static final int NUM_COL_ID = 0;
    private static final String COL_PSEUDO = "pseudo";
    private static final int NUM_COL_PSEUDO = 1;
    private static final String COL_SCORE = "score";
    private static final int NUM_COL_SCORE = 2;
    private static final String COL_TIME = "time";
    private static final int NUM_COL_TIME = 3;
    private static final String COL_NIVEAU = "niveau";
    private static final int NUM_COL_NIVEAU = 4;
    private static final String[] COLUMNS = {COL_ID, COL_PSEUDO, COL_SCORE, COL_TIME, COL_NIVEAU};

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "scoreDB";

    private static SQLiteDatabase dataBase;
    private DataBaseHandler base;


    private static final String QUERY_INSERT = 	"insert into " + TABLE_SCORES +
            " values (?,?,?,?);";

    private static final String QUERY_READ_BY_NIVEAU = 	"select * from " + TABLE_SCORES +
            " where "+ COL_NIVEAU+" = ?;";

    private static final String QUERY_UPDATE =	"update " + TABLE_SCORES +
            " set " + COL_PSEUDO + "=?  " +
            "and "+ COL_SCORE+"=? " +
            "where " + COL_ID+"=?;";

    private static final String QUERY_DELETE = 	"delete from " + TABLE_SCORES +
            " where "+ COL_ID+"=?;";

    public ScoreDAOSQLite(Context context) {
        base = new DataBaseHandler(context);
    }

    public void open() {
        dataBase = base.getWritableDatabase();
    }

    public void close() {
        dataBase.close();
    }

    public SQLiteDatabase getDataBase() {
        return dataBase;
    }

    public long addScore(Score score) {
        ContentValues values = new ContentValues();

        values.put(COL_PSEUDO, score.getPseudo());
        values.put(COL_SCORE, score.getScore());
        values.put(COL_TIME, score.getTime());
        values.put(COL_NIVEAU, score.getNiveau());

        return dataBase.insert(TABLE_SCORES, null, values);
    }

    public int updateScore(int id, Score score) {
        ContentValues values = new ContentValues();

        values.put(COL_PSEUDO, score.getPseudo());
        values.put(COL_SCORE, score.getScore());
        values.put(COL_TIME, score.getTime());
        values.put(COL_NIVEAU, score.getNiveau());

        return dataBase.update(TABLE_SCORES, values, COL_ID + " = " + id, null);
    }

    public int removeScore(int id) {
        return dataBase.delete(TABLE_SCORES, COL_ID + " = " + id, null);
    }

    public List<Score> getAllScore() {
        List<Score> liste = new ArrayList<>();
        Cursor c = dataBase.query(TABLE_SCORES, COLUMNS, null, null, null, null, null);
        if(c.getCount() > 0) {
            while(c.moveToNext()) {
                //liste.add(cursorTofirstscore(c));
                Score s = new Score();

                s.setId(c.getInt(NUM_COL_ID));
                s.setPseudo(c.getString(NUM_COL_PSEUDO));
                s.setScore(c.getInt(NUM_COL_SCORE));
                s.setTime(c.getInt(NUM_COL_TIME));
                s.setNiveau(c.getInt(NUM_COL_NIVEAU));

                liste.add(s);
            }
        }
        else return null;
        return liste;
    }

    public List<Score> getAllScoreByNiveau(int niveau) {
        List<Score> liste = new ArrayList<>();
        Cursor c = dataBase.rawQuery("SELECT * FROM scores WHERE niveau = "+ niveau+";", null);
        if(c.getCount() > 0) {
            while(c.moveToNext()) {
                //liste.add(cursorTofirstscore(c));
                Score s = new Score();

                s.setId(c.getInt(NUM_COL_ID));
                s.setPseudo(c.getString(NUM_COL_PSEUDO));
                s.setScore(c.getInt(NUM_COL_SCORE));
                s.setTime(c.getInt(NUM_COL_TIME));
                s.setNiveau(c.getInt(NUM_COL_NIVEAU));

                liste.add(s);
            }
        }
        else return null;
        return liste;
    }
}

