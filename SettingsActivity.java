package com.dcm.miniproject7;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.EditTextPreference;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
            EditTextPreference editText = findPreference("editText");
            assert editText != null;
            editText.setOnPreferenceChangeListener((preference, newValue) -> {
                String team = newValue.toString();
                preference.setSummary("Your Favorite team is " + team);
                return true;
            });

            EditTextPreference editTextPhone = findPreference("editTextPhone");
            assert editTextPhone != null;
            editTextPhone.setOnPreferenceChangeListener((phonePreference, phoneNewValue) -> {
                String phoneNumber = phoneNewValue.toString();
                phonePreference.setSummary("Your contact number is ready to change to: " + phoneNumber);
                return true;
            });
        }
    }
}