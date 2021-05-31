package com.example.biametriclogin.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
//класс по работе с базой данных
public class DatabaseAdapter {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public DatabaseAdapter(Context context){
        dbHelper = new DatabaseHelper(context.getApplicationContext());
    }
    //открытие бд
    public DatabaseAdapter open(){
        database = dbHelper.getWritableDatabase();
        return this;
    }
    //закрытие бд
    public void close(){
        dbHelper.close();
    }
    //получение всех сущностей бд
    private Cursor getAllEntries(){
        String[] columns = new String[] {DatabaseHelper.ID, DatabaseHelper.TITLE, DatabaseHelper.DESC};
        return  database.query(DatabaseHelper.TABLE, columns, null, null, null, null, null);
    }
    //записи приводяться в стандарт для того чтобы в последующем их было проще вывести через listview
    public List<Note> getNotes(){
        ArrayList<Note> notes = new ArrayList<>();
        Cursor cursor = getAllEntries();
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.ID));
            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TITLE));
            String year = cursor.getString(cursor.getColumnIndex(DatabaseHelper.DESC));
            notes.add(new Note(id, name, year));
        }
        cursor.close();
        return notes;
    }
    //счетчик
    public long getCount(){
        return DatabaseUtils.queryNumEntries(database, DatabaseHelper.TABLE);
    }
    //получение записи
    public Note getNote(long id){
        Note note = null;
        String query = String.format("SELECT * FROM %s WHERE %s=?",DatabaseHelper.TABLE, DatabaseHelper.ID);
        Cursor cursor = database.rawQuery(query, new String[]{ String.valueOf(id)});
        if(cursor.moveToFirst()){
            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TITLE));
            String year = cursor.getString(cursor.getColumnIndex(DatabaseHelper.DESC));
            note = new Note(id, name, year);
        }
        cursor.close();
        return note;
    }
    //вставка записи в бд
    public long insert(Note note){

        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.TITLE, note.getTitle());
        cv.put(DatabaseHelper.DESC, note.getDesc());

        return  database.insert(DatabaseHelper.TABLE, null, cv);
    }
    //удаление записи из бд
    public long delete(long userId){

        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{String.valueOf(userId)};
        return database.delete(DatabaseHelper.TABLE, whereClause, whereArgs);
    }
    //обновление бд
    public long update(Note note){

        String whereClause = DatabaseHelper.ID + "=" + String.valueOf(note.getId());
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.TITLE, note.getTitle());
        cv.put(DatabaseHelper.DESC, note.getDesc());
        return database.update(DatabaseHelper.TABLE, cv, whereClause, null);
    }
}