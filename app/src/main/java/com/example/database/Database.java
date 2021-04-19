package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, 1);
    }

    public Database(@Nullable Context context) {
        super(context, "fruit.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table fruit(id integer primary key autoincrement," + "name text,"+ "color text," +"taste text," +"size text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addFruit(String name, String color, String taste, String size) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("color", color);
        values.put("taste", taste);
        values.put("size", size);
        db.insertOrThrow("fruit", null, values);
    }

    public Cursor getAllFruits() {
        String[] columns = {"id", "name", "color", "taste", "size"};
        SQLiteDatabase db = getReadableDatabase();
        return db.query("fruit", columns, null, null, null, null, null, null);
    }

    public void deleteAllFruits() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("fruit", null, null);
    }
}
