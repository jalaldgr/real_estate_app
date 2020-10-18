package ir.hamedanmelk.hamedanmelk.ui.myhamedanmelk.help;

import android.os.Bundle;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import java.util.Objects;

import ir.hamedanmelk.hamedanmelk.R;

public class HelpFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.help_preference, rootKey);
        Preference aboutuspreference=findPreference("myhamedanmelk_aboutus");
        Preference supportpreference=findPreference("myhamedanmelk_faq");
        Preference appinfopreference=findPreference("myhamedanmelk_appinfo");
        final NavController navController = Navigation.findNavController(Objects.requireNonNull(getActivity()),R.id.nav_host_fragment);

        Objects.requireNonNull(aboutuspreference).setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                navController.navigate(R.id.aboutUsFragment);
                return false;
            }
        });
        Objects.requireNonNull(supportpreference).setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                navController.navigate(R.id.FAQFragment);
                return false;
            }
        });
        Objects.requireNonNull(appinfopreference).setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                navController.navigate(R.id.appInfoFragment);
                return false;
            }
        });
    }
}