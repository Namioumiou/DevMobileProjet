package com.example.todolist.data;

import android.provider.BaseColumns;

public final class DatabaseContract {
    private DatabaseContract() {}

    public static class Database implements BaseColumns {
        public static final String TABLE_NAME = "Task";
//        public static final String PKEY_TASK = "id";
        public static final String COL_TITLE = "title";
        public static final String COL_DESCRIPTION = "description";
    }
}
