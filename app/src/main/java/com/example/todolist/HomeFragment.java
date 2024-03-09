package com.example.todolist;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.BaseColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.todolist.data.Database;
import com.example.todolist.data.DatabaseContract;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment {
    @SuppressLint("Range")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        Database dbHelper = new Database(getContext());

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.btnAddTask);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new TaskFragment());
            }
        });

        ListView list = (ListView) rootView.findViewById(R.id.listview_task);
        ArrayAdapter<String> tableau = new ArrayAdapter<String>(list.getContext(), R.layout.textview_task, R.id.textview_title_task);

        Log.i("DataBase", "Reading database...");
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String select = new String("SELECT * from " + DatabaseContract.Database.TABLE_NAME);
        Cursor cursor = db.rawQuery(select, null);
        Log.i("DataBase", "Number of entries: " + cursor.getCount());
        String result = "";
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                int i = cursor.getColumnIndex(DatabaseContract.Database.COL_TITLE);
                tableau.add(cursor.getString(i));
                Log.i("DataBase", cursor.getString(i));
                result = cursor.getString(i);
            } while (cursor.moveToNext());
        }
        list.setAdapter(tableau);

//        Log.e("DataBaseItem", "teest");
//        ListView list = (ListView) rootView.findViewById(R.id.listview_task);
//        ArrayAdapter<String> tableau = new ArrayAdapter<String>(list.getContext(), R.layout.textview_task, R.id.textview_title_task);
//        while(cursor.moveToNext()) {
//            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseContract.Database._ID));
//            tableau.add("teeeest");
//        }
//        cursor.close();

//        ListView list = (ListView) rootView.findViewById(R.id.listview_task);
//        ArrayAdapter<String> tableau = new ArrayAdapter<String>(list.getContext(), R.layout.textview_task, R.id.textview_title_task);
////
////        Log.i("DataBase", cursor.getString(2));
//        for (int i=0; i<10; i++) {
//            tableau.add("coucou"); }
//        list.setAdapter(tableau);

//        Button getBtn = rootView.findViewById(R.id.getBtn);
//        getBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("Home", "click getUser");
//                TextView textView = (TextView) rootView.findViewById(R.id.getTxt);
////                textView.setText(db.readTaskData());
//            }
//        });

        return rootView; // inflater.inflate(R.layout.fragment_home, null);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}