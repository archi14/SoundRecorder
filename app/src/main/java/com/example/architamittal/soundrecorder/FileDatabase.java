package com.example.architamittal.soundrecorder;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FileDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="FileDatabase.db";
    public static final String TABLE_NAME="Files";
    public static final String COLUMN_ID="id";
    public static final String FILE_NAME="name";
    public static final String FILE_PATH="path";

    public FileDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String cmd="CREATE TABLE "+TABLE_NAME+"("+COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ FILE_NAME + " TEXT,"+ FILE_PATH+" TEXT)";
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
        values.put(file.getFilename(), file.getFilePath());
        long id = db.insert(TABLE_NAME,null,values);
        db.close();
        return id;

    }
}
