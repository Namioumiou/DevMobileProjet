package com.example.todolist;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.todolist.databinding.ActivityMainBinding;
public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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

        replaceFragment(new HomeFragment());
        binding.bottomNavView.setBackground(null);

        binding.bottomNavView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.home) {
                replaceFragment(new HomeFragment());
            } else if (itemId == R.id.profile) {
                replaceFragment(new ProfileFragment());
            } else if (itemId == R.id.settings) {
                replaceFragment(new SettingsFragment());
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_in,  // enter
                        R.anim.fade_out,  // exit
                        R.anim.fade_in,   // popEnter
                        R.anim.slide_out  // popExit
                )
                .replace(R.id.frame_layout, fragment)
                //.addToBackStack(null)
                .commit();
    }


}