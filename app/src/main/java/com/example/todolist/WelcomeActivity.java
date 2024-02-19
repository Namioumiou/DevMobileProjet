package com.example.todolist;

import static androidx.databinding.DataBindingUtil.setContentView;

import android.content.Intent;
import android.os.Bundle;

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