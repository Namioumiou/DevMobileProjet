package com.example.todolist;

import static androidx.databinding.DataBindingUtil.setContentView;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class SettingsFragment extends Fragment {

    private SharedPreferences sharedPreferences;
    private static final String THEME_PREF = "theme_pref";
    private static final String DARK_MODE = "dark_mode";
    private static final String LIGHT_MODE = "light_mode";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);


        sharedPreferences = requireActivity().getSharedPreferences(THEME_PREF, requireActivity().MODE_PRIVATE);

        // Set up radio buttons
        RadioGroup radioGroup = rootView.findViewById(R.id.radioGroup);
        RadioButton darkModeButton = rootView.findViewById(R.id.darkModeRadioButton);
        RadioButton lightModeButton = rootView.findViewById(R.id.lightModeRadioButton);

        // Check the current theme preference
        String themeMode = sharedPreferences.getString(THEME_PREF, LIGHT_MODE);
        if (themeMode.equals(DARK_MODE)) {
            darkModeButton.setChecked(true);
        } else {
            lightModeButton.setChecked(true);
        }

        // Apply selected theme
        applyTheme(themeMode);

        // Listen for theme change
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (checkedId == R.id.darkModeRadioButton) {
                    editor.putString(THEME_PREF, DARK_MODE);
                    applyTheme(DARK_MODE);
                } else {
                    editor.putString(THEME_PREF, LIGHT_MODE);
                    applyTheme(LIGHT_MODE);
                }
                editor.apply();
            }
        });

        ListView list = rootView.findViewById(R.id.listview);
        ArrayList<TextSwitch> tsList = new ArrayList();
        for (int i=0; i<40; i++) {
            TextSwitch ts = new TextSwitch();
            ts.setTexte("coucou " + i);
            tsList.add(ts);
        }
        CustomAdapter adapter = new CustomAdapter(getContext(), tsList);
        list.setAdapter(adapter);

        // Inflate the layout for this fragment
        return rootView;
    }

    private void applyTheme(String themeMode) {
        if (themeMode.equals(DARK_MODE)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }


}