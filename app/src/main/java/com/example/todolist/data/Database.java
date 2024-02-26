package com.example.todolist.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "todolist";

//------------------------------ TABLE TASK ------------------------------//
    public static final String TABLE_TASK = "Task";
    public static final String PKEY_TASK = "id";
    public static final String COL_TITLE = "title";
    public static final String COL_DESCRIPTION = "description";
    public static final String COL_IS_CHECKED = "is_checked";
//------------------------------------------------------------------------//

//------------------------------ TABLE USER ------------------------------//
    public static final String TABLE_USER = "User";
    public static final String PKEY_USER = "id";
    public static final String COL_FIRST_NAME = "first_name";
    public static final String COL_LAST_NAME = "last_name";
//------------------------------------------------------------------------//

    protected static final String SQL_CREATE_USER_ENTRIES =
            "CREATE TABLE " + TABLE_USER + " (" +
                    PKEY_USER + " INTEGER PRIMARY KEY," +
                    COL_FIRST_NAME + " TEXT," +
                    COL_LAST_NAME + " TEXT)";

    protected static final String SQL_CREATE_TASK_ENTRIES =
            "CREATE TABLE " + TABLE_TASK + " (" +
                    PKEY_TASK + " INTEGER PRIMARY KEY," +
                    COL_TITLE + " TEXT," +
                    COL_DESCRIPTION + " TEXT," +
                    COL_IS_CHECKED + "BOOLEAN)";

    protected static final String SQL_DELETE_USER_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_USER;
    protected static final String SQL_DELETE_TASK_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_TASK;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_DELETE_USER_ENTRIES);
        db.execSQL(SQL_DELETE_TASK_ENTRIES);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void insertTaskData(String title, String description, Boolean isChecked)
    {
        Log.d("DataBase","Insertion de données en BDD");
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        ContentValues values = new ContentValues();
        values.put(COL_TITLE, title);
        values.put(COL_DESCRIPTION, description);
        values.put(COL_IS_CHECKED, isChecked);
        db.insertOrThrow(TABLE_TASK, null, values);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public String readTaskData()
    {
        Log.i("DataBase", "Reading database...");
        String select = new String("SELECT * from " + TABLE_TASK);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        Log.i("DataBase", "Number of entries: " + cursor.getCount());
        String result = "";
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                int i = cursor.getColumnIndex(COL_TITLE);
                Log.i("DataBase", "Reading: " + cursor.getString(i));
                result = cursor.getString(i);
            } while (cursor.moveToNext());
        }
        return result;
    }

    public void insertUserData(String firstName, String lastName)
    {
        Log.i("DataBase","Insertion de données en BDD");
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        ContentValues values = new ContentValues();
        values.put(COL_FIRST_NAME, firstName);
        values.put(COL_LAST_NAME, lastName);
        db.insertOrThrow(TABLE_USER, null, values);
        db.setTransactionSuccessful();
        db.endTransaction();
    }
    public String readUserData()
    {
        Log.i("DataBase", "Reading database...");
        String select = new String("SELECT * from " + TABLE_USER);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        Log.i("DataBase", "Number of entries: " + cursor.getCount());
        String result = "";
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                int i = cursor.getColumnIndex(COL_FIRST_NAME);
                Log.i("DataBase", "Reading: " + cursor.getString(i));
                result = cursor.getString(i);
            } while (cursor.moveToNext());
        }
        return result;
    }
}