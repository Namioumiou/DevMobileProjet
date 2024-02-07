package com.example.todolist;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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