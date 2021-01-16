package com.example.soccer_0124;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db_match";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_NAME = "table_match";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DATE = "title";
    private static final String COLUMN_HOME_TEAM = "home_team";
    private static final String COLUMN_AWAY_TEAM = "away_team";
    private static final String COLUMN_HOME_SCORE = "home_score";
    private static final String COLUMN_AWAY_SCORE = "away_score";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql =
                "CREATE TABLE "+ TABLE_NAME +" (" +
                        COLUMN_ID +" INTEGER PRIMARY KEY,"+
                        COLUMN_DATE +" TEXT,"+
                        COLUMN_HOME_TEAM + " TEXT,"+
                        COLUMN_AWAY_TEAM +" TEXT,"+
                        COLUMN_HOME_SCORE + " TEXT,"+
                        COLUMN_AWAY_SCORE +" TEXT"+
                        ")";
        db.execSQL(sql);
    }

    public void addFavorite(Match match){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,match.getIdEvent());
        values.put(COLUMN_DATE,match.getDateEvent());
        values.put(COLUMN_HOME_TEAM,match.getStrHomeTeam());
        values.put(COLUMN_AWAY_TEAM,match.getStrAwayTeam());
        values.put(COLUMN_HOME_SCORE,match.getIntHomeScore());
        values.put(COLUMN_AWAY_SCORE,match.getIntAwayScore());

        db.insert(TABLE_NAME, null,values);
        db.close();
    }


    public ArrayList<Match> getAllfavorite(){
        ArrayList<Match> matchList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(TABLE_NAME,new String[]{COLUMN_ID,COLUMN_DATE,COLUMN_HOME_TEAM,COLUMN_AWAY_TEAM,COLUMN_HOME_SCORE,COLUMN_AWAY_SCORE},null,null,null,null,null);

        if(c !=null && c.moveToFirst()){
            do {
                Match macth = new Match();
                macth.setIdEvent(Integer.parseInt(c.getString(0)));
                macth.setDateEvent(c.getString(1));
                macth.setStrHomeTeam(c.getString(2));
                macth.setStrAwayTeam(c.getString(3));
                macth.setIntHomeScore(c.getString(4));
                macth.setIntAwayScore(c.getString(5));
                matchList.add(macth);
            }while (c.moveToNext());
        }
        db.close();
        return matchList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            onCreate(db);
        }
    }


    public void deleteFav(int idEvent) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id= "+ idEvent,null);
        db.close();
    }
}
