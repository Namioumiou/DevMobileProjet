package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class WelcomeActivity extends AppCompatActivity {
    private Button button;

    public static WelcomeActivity newInstance() {
        WelcomeActivity welcome = new WelcomeActivity();
        return welcome;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);
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

        ImageView buttomImage = findViewById(R.id.imageView2);

        buttomImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intention = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intention);
            }

        });
    }
}