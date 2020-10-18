package ir.hamedanmelk.hamedanmelk.ui.myhamedanmelk;

import android.os.Bundle;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import java.util.Objects;

import ir.hamedanmelk.hamedanmelk.R;

public class MyHamedanMelkFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.my_hamedan_melk_preference, rootKey);
        Preference notification_button = findPreference("myhamedanmelk_notification");
        Preference aboutpreference= findPreference("myhamedanmelk_aboutus_items");
        final NavController controller=Navigation.findNavController(Objects.requireNonNull(getActivity()),R.id.nav_host_fragment);

        Objects.requireNonNull(notification_button).setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                return false;
            }
        });

        Objects.requireNonNull(aboutpreference).setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                controller.navigate(R.id.HelpFragment);
                return false;
            }
        });
    }
}