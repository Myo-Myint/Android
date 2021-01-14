package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {


    private Context context;
    private static final String DATABASE_NAME = "EventSchema.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_events";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME= "event_name";
    private static final String COLUMN_DATE= "event_date";
    private static final String COLUMN_LOCATION= "event_location";
    private static final String COLUMN_DESCRIPTION= "event_description";




    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query =
                "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_LOCATION + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT);";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    void  addEvent(String name, String date, String location,String des){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv  = new ContentValues();

        cv.put(COLUMN_NAME,name);
        cv.put(COLUMN_DATE,date);
        cv.put(COLUMN_LOCATION,location);
        cv.put(COLUMN_DESCRIPTION,des);

        long result = db.insert(TABLE_NAME,null,cv);

        if(result == -1){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,"Added Successfully",Toast.LENGTH_SHORT).show();
        }

    }

    Cursor readAllData(){

        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;

        if(db != null){
            cursor = db.rawQuery(query,null);

        }
        return cursor;

    }

    void updateData(String row_id,String name, String date, String loc,String des){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME,name);
        cv.put(COLUMN_DATE,date);
        cv.put(COLUMN_LOCATION,loc);
        cv.put(COLUMN_DESCRIPTION,des);

        long result = db.update(TABLE_NAME,cv,"_id=?",new String[] {row_id});

        if(result == -1){
            Toast.makeText(context,"Fail to Update",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Successfully Updated",Toast.LENGTH_SHORT).show();
        }

    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME,"_id=?",new String[] {row_id});

        if(result == -1){
            Toast.makeText(context,"Fail to Delete",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Successfully Deleted",Toast.LENGTH_SHORT).show();
        }

    }

    void deleteAllData(){

        SQLiteDatabase db= this.getWritableDatabase();

        context.deleteDatabase("EventSchema.db");
//        db.execSQL("DELETE FROM " + TABLE_NAME);

    }


}
