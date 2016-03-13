package com.faravy.bitmtrainer401.sqliteexample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by BITM Trainer 401 on 3/10/2016.
 */
public class DataBaseHelper  extends SQLiteOpenHelper {

    static final String DATABASE_NAME="contact_manager";
    static final int DATABASE_VERSION=1;

    static final String COL_ID="id";
    static final String COL_NAME="name";
    static final String COL_PHONENO="phoneno";
    static final String TABLE_CONTACT_INFO="contact_info";


     String CREATE_TABLE_CONTACT= " CREATE TABLE " + TABLE_CONTACT_INFO +
             " ( "+ COL_ID + " INTEGER PRIMARY KEY, " + COL_NAME + " TEXT, "+
             COL_PHONENO +" TEXT )";

    public DataBaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CONTACT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" DROP TABLE IF EXIST " + TABLE_CONTACT_INFO);
        onCreate(db);
    }
}
