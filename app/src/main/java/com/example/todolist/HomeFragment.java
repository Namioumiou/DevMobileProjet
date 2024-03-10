package com.example.todolist;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
                replaceFragment(new TaskFragment("", ""));
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

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                String  itemValue = (String) list.getItemAtPosition(position);

                SQLiteDatabase db = dbHelper.getReadableDatabase();
                String select = new String("SELECT * from " + DatabaseContract.Database.TABLE_NAME);
                Cursor cursor = db.rawQuery(select, null);
                String result = "";
                if (cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    do {
                        int i = cursor.getColumnIndex(DatabaseContract.Database.COL_TITLE);
                        if(Objects.equals(cursor.getString(i), itemValue)) {
                            int j = cursor.getColumnIndex(DatabaseContract.Database.COL_DESCRIPTION);
                            String title = cursor.getString(i);
                            String description = cursor.getString(j);
                            replaceFragment(new TaskFragment(title, description));
                        }
                    } while (cursor.moveToNext());
                }
            }
        });

        return rootView; // inflater.inflate(R.layout.fragment_home, null);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}