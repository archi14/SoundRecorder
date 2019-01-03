package com.example.architamittal.soundrecorder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class FileDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="FileDatabase.db";
    public static final String TABLE_NAME="Files";
    //public static final String COLUMN_ID="id";
    public static final String FILE_NAME="name";
    public static final String FILE_PATH="path";
    public static final String DATETIME ="datetime";
    public FileDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String cmd="CREATE TABLE "+TABLE_NAME+"("+ FILE_NAME + " TEXT,"+ FILE_PATH+" TEXT," +DATETIME+" TEXT)" ;
        sqLiteDatabase.execSQL(cmd);
        Log.d("TAG", "onCreate: ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long insertFile(SoundFile file)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FILE_NAME,file.getFilename());
        values.put(FILE_PATH,file.getFilePath());
        values.put(DATETIME,file.getDateTime());

        long id = db.insert(TABLE_NAME,null,values);
        db.close();
        return id;

    }

    public List<SoundFile> getAllFiles(){

        List<SoundFile> files = new ArrayList<SoundFile>();
        String selectQuery = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()) {
            do {
                SoundFile file = new SoundFile();
                file.setFilename(cursor.getString(0));
                file.setFilePath(cursor.getString(1));
                file.setDateTime(cursor.getString(2));
                files.add(file);

            } while (cursor.moveToNext());

        }
        return files;
    }

    SoundFile getFile(String filename){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,new String[]{FILE_NAME,FILE_PATH,DATETIME},FILE_NAME+"=?",new String[]{filename},null,null,null,null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
        }
        SoundFile file = new SoundFile(cursor.getString(0),cursor.getString(1),cursor.getString(2));
        return file;
    }
}
