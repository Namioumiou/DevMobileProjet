package com.example.todolist.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 8;
    public static final String DATABASE_NAME = "todolist";

    private static final String SQL_CREATE_TASK_ENTRIES =
            "CREATE TABLE " + DatabaseContract.Database.TABLE_NAME + " (" +
                    DatabaseContract.Database._ID + " INTEGER PRIMARY KEY," +
                    DatabaseContract.Database.COL_TITLE + " TEXT," +
                    DatabaseContract.Database.COL_DESCRIPTION + " TEXT)";
    private static final String SQL_DELETE_TASK_ENTRIES =
            "DROP TABLE IF EXISTS " + DatabaseContract.Database.TABLE_NAME;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        Log.i("Database", "onCreate BDD");
        db.execSQL(SQL_CREATE_TASK_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("Database", "onUpgrade BDD");
        db.execSQL(SQL_DELETE_TASK_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("Database", "onDowngrade BDD");
        onUpgrade(db, oldVersion, newVersion);
    }

//    public void insertTaskData(String title, String description, Boolean isChecked) {
//        Log.i("DataBase","Insertion de données en BDD");
//        SQLiteDatabase db = getWritableDatabase();
//
//        db.beginTransaction();
//        ContentValues values = new ContentValues();
//        values.put(DatabaseContract.Database.COL_TITLE, title);
//        values.put(DatabaseContract.Database.COL_DESCRIPTION, description);
//
//        long newRowId = db.insert(DatabaseContract.Database.TABLE_NAME, null, values);
//        db.insertOrThrow(TABLE_TASK, null, values);
//        db.setTransactionSuccessful();
//        db.endTransaction();
//    }

//    public String readTaskData()
//    {
//        Log.i("DataBase", "Reading database...");
//        SQLiteDatabase db = getReadableDatabase();
//        String select = new String("SELECT * from " + DatabaseContract.Database.TABLE_NAME);
//        Cursor cursor = db.rawQuery(select, null);
//        Log.i("DataBase", "Number of entries: " + cursor.getCount());
//        String result = "";
//        if (cursor.getCount() > 0) {
//            cursor.moveToFirst();
//            do {
//                int i = cursor.getColumnIndex(DatabaseContract.Database.COL_TITLE);
//                Log.i("DataBase", "Reading: " + cursor.getString(i));
//                result = cursor.getString(i);
//            } while (cursor.moveToNext());
//        }
//        return result;
//    }
}