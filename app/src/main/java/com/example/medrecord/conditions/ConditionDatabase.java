package com.example.medrecord.conditions;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ConditionDatabase extends SQLiteOpenHelper {
    // declare require values
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "SimpleConditionDB";
    private static final String TABLE_NAME = "SimpleConditionTable";

    public ConditionDatabase(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    // declare table column names
    private static final String KEY_CONDITION_ID = "conditionid";
    private static final String KEY_CONDITION_TITLE = "conditiontitle";
    private static final String KEY_CONDITION_CONTENT = "conditioncontent";
    private static final String KEY_CONDITION_DATE = "conditiondate";
    private static final String KEY_CONDITION_TIME = "conditiontime";





    // creating tables
    @Override
    public void onCreate(SQLiteDatabase conditiondb) {
        String createconditionDb = "CREATE TABLE "+TABLE_NAME+" ("+
                KEY_CONDITION_ID+" INTEGER PRIMARY KEY,"+
                KEY_CONDITION_TITLE+" TEXT,"+
                KEY_CONDITION_CONTENT+" TEXT,"+
                KEY_CONDITION_DATE+" TEXT,"+
                KEY_CONDITION_TIME+" TEXT"
                +" )";
        conditiondb.execSQL(createconditionDb);
    }

    // upgrade db if older version exists
    @Override
    public void onUpgrade(SQLiteDatabase conditiondb, int oldVersion, int newVersion) {
        if(oldVersion >= newVersion)
            return;

        conditiondb.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(conditiondb);
    }

    public long addCondition(ConditionNote conditionNote){
        SQLiteDatabase conditiondb = this.getWritableDatabase();
        ContentValues conditionv = new ContentValues();
        conditionv.put(KEY_CONDITION_TITLE,conditionNote.getConditionTitle());
        conditionv.put(KEY_CONDITION_CONTENT,conditionNote.getConditionContent());
        conditionv.put(KEY_CONDITION_DATE,conditionNote.getConditionDate());
        conditionv.put(KEY_CONDITION_TIME,conditionNote.getConditionTime());

        // inserting data into db
        long ID = conditiondb.insert(TABLE_NAME,null,conditionv);
        return  ID;
    }

    public ConditionNote getCondition(long id){
        SQLiteDatabase conditiondb = this.getWritableDatabase();
        String[] conditionquery = new String[] {KEY_CONDITION_ID,KEY_CONDITION_TITLE,KEY_CONDITION_CONTENT,KEY_CONDITION_DATE,KEY_CONDITION_TIME};
        Cursor conditioncursor=  conditiondb.query(TABLE_NAME,conditionquery,KEY_CONDITION_ID+"=?",new String[]{String.valueOf(id)},null,null,null,null);
        if(conditioncursor != null)
            conditioncursor.moveToFirst();

        return new ConditionNote(
                Long.parseLong(conditioncursor.getString(0)),
                conditioncursor.getString(1),
                conditioncursor.getString(2),
                conditioncursor.getString(3),
                conditioncursor.getString(4));
    }

    public List<ConditionNote> getAllCondition(){
        List<ConditionNote> allCondition = new ArrayList<>();
        String conditionquery = "SELECT * FROM " + TABLE_NAME+" ORDER BY "+KEY_CONDITION_ID+" DESC";
        SQLiteDatabase conditiondb = this.getReadableDatabase();
        Cursor conditioncursor = conditiondb.rawQuery(conditionquery,null);
        if(conditioncursor.moveToFirst()){
            do{
                ConditionNote conditionnote = new ConditionNote();
                conditionnote.setConditionId(Long.parseLong(conditioncursor.getString(0)));
                conditionnote.setConditionTitle(conditioncursor.getString(1));
                conditionnote.setConditionContent(conditioncursor.getString(2));
                conditionnote.setConditionDate(conditioncursor.getString(3));
                conditionnote.setConditionTime(conditioncursor.getString(4));
                allCondition.add(conditionnote);
            }while (conditioncursor.moveToNext());
        }

        return allCondition;

    }

    public int editCondition(ConditionNote conditionNote){
        SQLiteDatabase conditiondb = this.getWritableDatabase();
        ContentValues cond = new ContentValues();
        Log.d("Edited", "Edited Title: -> "+ conditionNote.getConditionTitle() + "\n ID -> "+conditionNote.getConditionId());
        cond.put(KEY_CONDITION_TITLE,conditionNote.getConditionTitle());
        cond.put(KEY_CONDITION_CONTENT,conditionNote.getConditionContent());
        cond.put(KEY_CONDITION_DATE,conditionNote.getConditionDate());
        cond.put(KEY_CONDITION_TIME,conditionNote.getConditionTime());
        return conditiondb.update(TABLE_NAME,cond,KEY_CONDITION_ID+"=?",new String[]{String.valueOf(conditionNote.getConditionId())});
    }



    void deleteCondition(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,KEY_CONDITION_ID+"=?",new String[]{String.valueOf(id)});
        db.close();
    }
}
