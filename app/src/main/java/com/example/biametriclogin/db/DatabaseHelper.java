package com.example.biametriclogin.db;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "notes.db"; // название бд
    private static final int SCHEMA = 2; // версия базы данных
    static final String TABLE = "notes"; // название таблицы в бд
    // названия столбцов
    public static final String ID = "_id";
    public static final String TITLE = "title";
    public static final String DESC = "desc";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE + " (" + ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TITLE + " TEXT," +
                " "+ DESC + " TEXT);");
        // добавление начальных данных
        db.execSQL("INSERT INTO "+ TABLE +" (" + TITLE
                + ", " + DESC  + ") VALUES ('First record', 'descriptions');");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }
}