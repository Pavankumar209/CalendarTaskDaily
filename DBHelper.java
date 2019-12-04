package com.example.calendartask;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "CalendarMark.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "milk_details";
    public static final String COL_DATE = "date_of_entry";
    public static final String COL_TIME = "time_of_entry";
    public static final String COL_QUANTITY = "quantity";
    public static final String COL_REMARKS = "remarks";

    public static final String TABLE_CREATE = "CREATE TABLE "+TABLE_NAME+" ("+COL_DATE+" TEXT,"+COL_TIME+" TEXT,"+COL_QUANTITY+" REAL,"+COL_REMARKS+" TEXT,"+" PRIMARY KEY("+COL_DATE+","+COL_TIME+"))";
    public static final String TABLE_DELETE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL(TABLE_DELETE);
        onCreate(db);
    }

    public Cursor getStatus(String date) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c=db.query(TABLE_NAME,null,COL_DATE + "=?",new String[]{date},null,null,null,null);
        return c;
    }


    public void insert_val(String selected_date, int done, float q, String sed1) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues cv = new ContentValues();
        cv.put(COL_DATE,selected_date);
        cv.put(COL_QUANTITY,(float)q);
        cv.put(COL_REMARKS,sed1);
        if(done==1){
            int b=db.delete(TABLE_NAME,COL_DATE+"=? and "+COL_TIME+"=?",new String[]{selected_date,"M"});
            cv.put(COL_TIME,"M");
            db.insert(TABLE_NAME,null,cv);
        }
        else if(done==2){
            int b=db.delete(TABLE_NAME,COL_DATE+"=? and "+COL_TIME+"=?",new String[]{selected_date,"E"});
            cv.put(COL_TIME,"E");
            db.insert(TABLE_NAME,null,cv);
        }
        db.close();
    }

    public Cursor getAll() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c=db.query(TABLE_NAME,null,null,null,null,null,COL_DATE);
        return c;

    }

    public Cursor getLike(String s) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c=db.query(true,TABLE_NAME,null,COL_DATE+" LIKE ?",new String[]{ "___"+s+"%" },null,null,null,null);
        return c;
    }
}
