package com.example.todolist;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class WelcomeFragment extends Fragment {
    private Button button;

    public static WelcomeFragment newInstance() {
        WelcomeFragment welcome = new WelcomeFragment();
        return welcome;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_welcome, container, false);

        ImageView buttonImage = inflate.findViewById(R.id.imageView2);

        buttonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment homeFragment = new HomeFragment();
                // Create a new fragment transaction
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                // Replace whatever is in the fragment_container view with this fragment
                transaction.replace(R.id.fragment_container_view, homeFragment);
                // Add the transaction to the back stack
                transaction.addToBackStack(null);
                // Commit the transaction
                transaction.commit();
            }
        });

        return inflate;
    }



}