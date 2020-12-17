package ir.hamedanmelk.hamedanmelk.ui.personalpage;

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
        final NavController controller=Navigation.findNavController(Objects.requireNonNull(getActivity()),R.id.nav_host_fragment);
        setPreferencesFromResource(R.xml.my_hamedan_melk_preference, rootKey);
        // define preferences///////////////////////////////////////////////////
        Preference about_preference= findPreference(getResources().getString(R.string.pref_screen_myhamedanmelk_aboutus_items));
        Preference user_favorites = findPreference(getResources().getString(R.string.pref_screen_myhamedanmelk_user_favorites));
        Preference user_lands     = findPreference("myhamedanmelk_user_lands");
//        Preference register_company=findPreference(getResources().getString(R.string.pref_screen_myhamedanmelk_register_company));

//        register_company.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//            @Override
//            public boolean onPreferenceClick(Preference preference) {
//                controller.navigate(R.id.newCompanyFragment);
//                return false;
//            }
//        });

//        user_lands.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//            @Override
//            public boolean onPreferenceClick(Preference preference) {
//                controller.navigate(R.id.userLandFragment);
//                return false;
//            }
//        });

//        user_favorites.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//            @Override
//            public boolean onPreferenceClick(Preference preference) {
//                controller.navigate(R.id.userFavoritesFragment);
//                return false;
//            }
//        });

//        Objects.requireNonNull(about_preference).setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//            @Override
//            public boolean onPreferenceClick(Preference preference) {
//                controller.navigate(R.id.HelpFragment);
//                return false;
//            }
//        });
    }
}