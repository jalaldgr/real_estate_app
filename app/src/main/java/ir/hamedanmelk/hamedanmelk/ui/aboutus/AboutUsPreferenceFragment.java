package ir.hamedanmelk.hamedanmelk.ui.aboutus;

import android.os.Bundle;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import java.util.Objects;

import ir.hamedanmelk.hamedanmelk.R;

public class AboutUsPreferenceFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.aboutus_preference, rootKey);
        Preference notification_button = findPreference("aboutus_notification");
        notification_button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                NavController controller=Navigation.findNavController(Objects.requireNonNull(getActivity()),R.id.nav_host_fragment);
                controller.navigate(R.id.notificationSettingsFragment);
                return false;
            }
        });
    }
}