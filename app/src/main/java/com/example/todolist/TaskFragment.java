package com.example.todolist;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.todolist.data.Database;
import com.example.todolist.data.DatabaseContract;

public class TaskFragment extends Fragment {
    public TaskFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_task, container, false);
        Database dbHelper = new Database(getContext());
        Button btn_cancel = rootView.findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new HomeFragment());
            }
        });

        Button btn_save = rootView.findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText pltxt_title = (EditText) rootView.findViewById(R.id.pltxt_title);
                EditText pltxt_note = (EditText) rootView.findViewById(R.id.pltxt_note);

                String title = String.valueOf(pltxt_title.getText());
                String note = String.valueOf(pltxt_note.getText());

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(DatabaseContract.Database.COL_TITLE, title);
                values.put(DatabaseContract.Database.COL_DESCRIPTION, note);
                db.insert(DatabaseContract.Database.TABLE_NAME, null, values);

//                db.insertTaskData(title, note, false);
                replaceFragment(new HomeFragment());
            }
        });

        return rootView;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}