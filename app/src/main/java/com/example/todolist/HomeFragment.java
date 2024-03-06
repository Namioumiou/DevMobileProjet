package com.example.todolist;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class HomeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        Database db = new Database(getContext());

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.btnAddTask);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new TaskFragment());
            }
        });

        ListView list = (ListView) rootView.findViewById(R.id.listview_task);
        ArrayAdapter<String> tableau =
                new ArrayAdapter<String>(list.getContext(),
                        R.layout.textview_task,

                        R.id.textview_title_task);

        for (int i=0; i<10; i++) {
            tableau.add("coucou"); }
        list.setAdapter(tableau);

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