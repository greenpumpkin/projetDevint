package com.devint.cindy.speedmemory.database;

/**
 * Created by Aina on 17/04/15.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHandler extends SQLiteOpenHelper
{
    private static final String TABLE_SCORES = "scores";
    private static final String COL_ID = "_id";
    private static final String COL_PSEUDO = "pseudo";
    public static final String COL_SCORE = "score";
    private static final String COL_TIME = "time";
    public static final String COL_NIVEAU = "niveau";

    private static final String  CREATE_BDD = "create table " +
            TABLE_SCORES + "(" +
            COL_ID + " integer primary key autoincrement, " +
            COL_PSEUDO + " text, " +
            COL_SCORE + " integer, " +
            COL_TIME + " integer, " +
            COL_NIVEAU + " integer);";

    public DataBaseHandler(Context context)
    {
        super(context.getApplicationContext(),"android.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(CREATE_BDD);
    }

    /*public void delete(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE " + TABLE_SCORES);
    }*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }
}
