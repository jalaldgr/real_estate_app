package ir.hamedanmelk.hamedanmelk.ui.personalpage;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import ir.hamedanmelk.hamedanmelk.R;

public class NotificationSettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.notification_setting_preference, rootKey);
    }
}