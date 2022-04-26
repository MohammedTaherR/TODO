package com.example.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class dbhandler extends SQLiteOpenHelper {
    public dbhandler(@Nullable Context context) {
            super(context, "DB_NAME", null, 1);
        }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String mytable = " CREATE TABLE todoTable(Taskname TEXT ,Goal TEXT ,Time TEXT ,progressInit TEXT ,progressFinal TEXT ,Date TEXT); ";
        Log.d("table", mytable);
        db.execSQL(mytable);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }
    public Boolean addlist(String Taskname, String Goal, String Time, String progressInit, String progressFinal,String Date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Taskname", Taskname);
        values.put("Goal", Goal);
        values.put("Time", Time);
        values.put("Date",Date);
        values.put("progressInit", progressInit);
        values.put("progressFinal", progressFinal);
        Log.d("DB","values are inserted");
        Long res = db.insert("todoTable", null, values);
        if (res == -1) {
            return false;
        } else {
            return true;
        }
    }
    public void delete(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("todoTable", "Taskname=?", new String[]{title});
        db.close();
    }
    public boolean update(String Taskname, String Goal, String Time, String progressInit, String progressFinal,String Date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Taskname", Taskname);
        values.put("Goal", Goal);
        values.put("Time", Time);
        values.put("Date",Date);
        values.put("progressInit", progressInit);
        values.put("progressFinal", progressFinal);
        db.update("todoTable", values, "Taskname = ?", new String[]{Taskname});
        return true;
    }
    public boolean IncrementOrDecrement(String Taskname, String progressInit, String progressFinal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("progressInit", progressInit);
        values.put("progressFinal", progressFinal);
        db.update("todoTable", values, "Taskname = ?", new String[]{Taskname});
        return  true;
    }
    public Cursor getdata (){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM todoTable" ,null);
        return  cursor;
    }
}
