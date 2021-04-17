package com.example.medrecord;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class AppointmentDatabase extends SQLiteOpenHelper {
    // declare require values
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "SimpleDB";
    private static final String TABLE_NAME = "SimpleTable";

    public AppointmentDatabase(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    // declare table column names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "eventname";
    private static final String KEY_TIME = "eventtime";
    private static final String KEY_LOCATION = "eventlocation";
    private static final String KEY_NOTES = "eventnotes";





    // creating tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDb = "CREATE TABLE "+TABLE_NAME+" ("+
                KEY_ID+" INTEGER PRIMARY KEY,"+
                KEY_NAME+" TEXT,"+
                KEY_TIME+" TEXT,"+
                KEY_LOCATION+" TEXT,"+
                KEY_NOTES+" TEXT"
                +" )";
        db.execSQL(createDb);
    }

    // upgrade db if older version exists
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion >= newVersion)
            return;

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public long addNote(AppointmentNotes note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(KEY_NAME,note.getEventname());
        v.put(KEY_TIME,note.getEventtime());
        v.put(KEY_LOCATION,note.getEventlocation());
        v.put(KEY_NOTES,note.getEventnotes());

        // inserting data into db
        long ID = db.insert(TABLE_NAME,null,v);
        return  ID;
    }

    public AppointmentNotes getNote(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] query = new String[] {KEY_ID,KEY_NAME,KEY_TIME,KEY_LOCATION,KEY_NOTES};
        Cursor cursor=  db.query(TABLE_NAME,query,KEY_ID+"=?",new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor != null)
            cursor.moveToFirst();

        return new AppointmentNotes(
                Long.parseLong(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4));
    }

    public List<AppointmentNotes> getAllNotes(){
        List<AppointmentNotes> allNotes = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME+" ORDER BY "+KEY_ID+" DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                AppointmentNotes note = new AppointmentNotes();
                note.setId(Long.parseLong(cursor.getString(0)));
                note.setEventname(cursor.getString(1));
                note.setEventtime(cursor.getString(2));
                note.setEventlocation(cursor.getString(3));
                note.setEventnotes(cursor.getString(4));
                allNotes.add(note);
            }while (cursor.moveToNext());
        }

        return allNotes;

    }

    void deleteNote(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,KEY_ID+"=?",new String[]{String.valueOf(id)});
        db.close();
    }
}
