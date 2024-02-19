package com.example.todolist;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WelcomeFragment welcomeFragment = new WelcomeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container_view, welcomeFragment);
        fragmentTransaction.commit();



        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Souhaitez vous fermer l'application ?")
                        .setTitle("Vous nous quittez déjà...")
                        .setPositiveButton("Fermer", (dialog, which) -> {
                            finish();
                            dialog.dismiss();
                        })
                        .setNegativeButton("Annuler", (dialog, which) -> dialog.dismiss())
                        .show();
            }
        });
    }
}