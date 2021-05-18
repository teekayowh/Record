package com.example.medrecord.medicine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MedicineDatabase extends SQLiteOpenHelper {
    // declare require values
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "SimpleMedicineDB";
    private static final String TABLE_NAME = "SimpleMedicineTable";

    public MedicineDatabase(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    // declare table column names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "medicinename";
    private static final String KEY_TIME = "medicinetime";
    private static final String KEY_LOCATION = "medicinelocation";
    private static final String KEY_NOTES = "medicinenotes";


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

    public long addMedicine(MedicineNote Medicinenote){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(KEY_ID, Medicinenote.getId());
        v.put(KEY_NAME,Medicinenote.getMedicinename());
        v.put(KEY_TIME,Medicinenote.getMedicinetime());
        v.put(KEY_LOCATION,Medicinenote.getMedicinelocation());
        v.put(KEY_NOTES,Medicinenote.getMedicinenotes());

        // inserting data into db
        long ID = db.insert(TABLE_NAME,null,v);

        return  ID;
    }

//    public AppointmentNotes getNote(long id){
//        SQLiteDatabase db = this.getWritableDatabase();
//        String[] query = new String[] {KEY_ID,KEY_NAME,KEY_TIME,KEY_LOCATION,KEY_NOTES};
//        Cursor cursor=  db.query(TABLE_NAME,query,KEY_ID+"=?",new String[]{String.valueOf(id)},null,null,null,null);
//        if(cursor != null)
//            cursor.moveToFirst();
//
//        return new AppointmentNotes(
//                Long.parseLong(cursor.getString(0)),
//                cursor.getString(1),
//                cursor.getString(2),
//                cursor.getString(3),
//                cursor.getString(4));
//    }

    public List<MedicineNote> getAllMedicine(){
        List<MedicineNote> allMedicine = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME+" ORDER BY "+KEY_ID+" DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                MedicineNote Medicinenote = new MedicineNote();
                Medicinenote.setId(Long.parseLong(cursor.getString(0)));
                Medicinenote.setMedicinename(cursor.getString(1));
                Medicinenote.setMedicinetime(cursor.getString(2));
                Medicinenote.setMedicinelocation(cursor.getString(3));
                Medicinenote.setMedicinenotes(cursor.getString(4));
                allMedicine.add(Medicinenote);
            }while (cursor.moveToNext());
        }

        return allMedicine;

    }

    void deleteMedicine(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,KEY_ID+"=?",new String[]{String.valueOf(id)});
        db.close();
    }

    public long getNewId() {
        String queryLastRowInserted = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        final Cursor cursor = db.rawQuery(queryLastRowInserted, null);
        int _idLastInsertedRow = 0;
        if (cursor != null) {
            try {
                if (cursor.moveToLast()) {
                    _idLastInsertedRow = cursor.getInt(0);
                }
            } finally {
                cursor.close();
            }
        }

        return _idLastInsertedRow + 1;

    }
}

