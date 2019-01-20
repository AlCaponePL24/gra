package com.example.radek.liczjakinformatyk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Clasa odpowiadająca za obsługę bazy SQLite
 */
public class bazaDanych extends SQLiteOpenHelper {
    public static final String NAZWA_BAZY = "score.db";
    public static final String KOL_1 = "ID";
    public static final String KOL_2 = "SCORE";

    private String nazwaBazy;

    public bazaDanych(Context context, String baza_nazwa) {
        super(context, baza_nazwa, null, 1);
        nazwaBazy = baza_nazwa;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "
                + nazwaBazy + " ("
                + KOL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KOL_2 + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int staraWer, int nowaWer) {
        db.execSQL("DROP TABLE IF EXISTS " + nazwaBazy);
        onCreate(db);
    }

    public Cursor dane() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + nazwaBazy + " ORDER BY " + KOL_2 + " DESC", null);
        return res;
    }

    public boolean dopiszDane(String score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KOL_2, score);
        long result = db.insert(nazwaBazy, null, contentValues);
        if(result == -1) return false;
        else return true;

    }

    public boolean aktualizujDane(String id, String score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KOL_1, id);
        contentValues.put(KOL_2, score);
        db.update(nazwaBazy, contentValues, "ID = ?", new String[] {id});
        return true;
    }
}