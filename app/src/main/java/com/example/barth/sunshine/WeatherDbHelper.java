package com.example.barth.sunshine;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by barth on 23/04/15.
 */
public class WeatherDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;

    static final String DATABASE_NAME = "weather.db";

    public WeatherDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_WEATHER_TABLE = "CREATE TABLE " + WeatherContract.WeatherEntry.TABLE_NAME + "("+
                WeatherContract.WeatherEntry._ID + "INTEGER PRIMARY KEY NOT AUTOINCREMENT," +
                WeatherContract.WeatherEntry.COLUMN_LOC_KEY + "INTEGER NOT NULL, " +
                WeatherContract.WeatherEntry.COLUMN_DATE + "INTEGER NOT NULL, " +
                WeatherContract.WeatherEntry.COLUMN_SHORT_DESC + "TEXT NOT NULL, " +
                WeatherContract.WeatherEntry.COLUMN_WEATHER_ID + "INTEGER NOT NULL," +

                WeatherContract.WeatherEntry.COLUMN_MIN_TEMP + "REAL NOT NULL, " +
                WeatherContract.WeatherEntry.COLUMN_MAX_TEMP + "REAL NOT NULL, " +

                WeatherContract.WeatherEntry.COLUMN_HUMIDITY + "REAL NOT NULL, " +
                WeatherContract.WeatherEntry.COLUMN_PRESSURE + "REAL NOT NULL, " +
                WeatherContract.WeatherEntry.COLUMN_WIND_SPEED + "REAL NOT NULL, " +
                WeatherContract.WeatherEntry.COLUMN_DEGREE + "REAL NOT NULL, " +

                "FOREIGN KEY (" + WeatherContract.WeatherEntry.COLUMN_LOC_KEY + ") REFERENCES "+
                WeatherContract.LocationEntry.TABLE_NAME + "(" + WeatherContract.LocationEntry._ID + "), " +


                "UNIQUE (" + WeatherContract.WeatherEntry.COLUMN_DATE + ", " +
                WeatherContract.WeatherEntry.COLUMN_LOC_KEY + ") ON CONFLICT REPLACE);";


        final String SQL_CREATE_LOCATION_TABLE = "CREATE TABLE" + WeatherContract.WeatherEntry.TABLE_NAME + "(" +
                WeatherContract.LocationEntry._ID + "INTEGER PRIMARY KEY," +
                WeatherContract.LocationEntry.COLUMN_LOCATION_SETTING + "TEXT UNIQUE NOT NULL, " +
                WeatherContract.LocationEntry.COLUMN_CITY_NAME + "REAL NOT NULL, " +
                WeatherContract.LocationEntry.COLUMN_COORD_LAT + "REAL NOT NULL, " +
                WeatherContract.LocationEntry.COLUMN_COORD_LONG + "REAL NOT NULL" + ");";

        sqLiteDatabase.execSQL(SQL_CREATE_WEATHER_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_LOCATION_TABLE);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + WeatherContract.LocationEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + WeatherContract.WeatherEntry.TABLE_NAME);

        onCreate(sqLiteDatabase);

    }
}
