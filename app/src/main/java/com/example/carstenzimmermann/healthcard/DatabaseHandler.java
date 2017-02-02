package com.example.carstenzimmermann.healthcard;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Carsten Zimmermann on 02.02.2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper
{
    private SQLiteDatabase db;

    public DatabaseHandler(Context activity, String dbName)
    {
        super(activity, dbName, null, 1);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
//        String sql = "CREATE TABLE children " +
//                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                "firstname VARCHAR 30, " +
//                "lastname VARCHAR 30, " +
//                "birtdateDayOfMonth INTEGER, " +
//                "birthdateMonth INTEGER, " +
//                "birthdateYear INTEGER, " +
//                "sex INTEGER, " +
//                ""
        //TODO: finish creating the class
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
