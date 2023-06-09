package ir.hamedanmelk.hamedanmelk.ui.personalpage;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import java.util.Objects;

import ir.hamedanmelk.hamedanmelk.R;

import static android.content.Context.MODE_PRIVATE;

public class MyHamedanMelkFragment extends PreferenceFragmentCompat {
    loggedInPreference loggedInPreference;
    notLoggedInPreference notLoggedInPreference;
    Preference about_preference;
    Preference user_favorites;
    Preference user_lands ;
    Preference history ;

    Preference share;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        final NavController controller=Navigation.findNavController(Objects.requireNonNull(getActivity()),R.id.nav_host_fragment);
        final SharedPreferences user_pref = Objects.requireNonNull(getActivity()).getSharedPreferences(getString(R.string.user_shared_preference), Context.MODE_PRIVATE);

        setPreferencesFromResource(R.xml.my_hamedan_melk_preference, rootKey);
        // define preferences///////////////////////////////////////////////////
        loggedInPreference = findPreference("image_preference");
        notLoggedInPreference = findPreference("not_login_preference");
        about_preference= findPreference("myhamedanmelk_aboutus_items");
        user_favorites = findPreference("myhamedanmelk_user_favorites");
        user_lands     = findPreference("myhamedanmelk_user_lands");
        history     = findPreference("myhamedanmelk_history");
        share   = findPreference("myhamedanmelk_share");

        if(user_pref.contains("id")) {
            notLoggedInPreference.setVisible(false);
            loggedInPreference.setVisible(true);
            user_favorites.setVisible(true);
            user_lands.setVisible(true);
        }
        else{
            notLoggedInPreference.setVisible(true);
            loggedInPreference.setVisible(false);
            user_favorites.setVisible(false);
            user_lands.setVisible(false);
        }

        user_lands.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                controller.navigate(R.id.userLandFragment);
                return false;
            }
        });

        user_favorites.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                controller.navigate(R.id.userFavoritesFragment);
                return false;
            }
        });

        history.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                controller.navigate(R.id.userHistoryFragment);
                return false;
            }
        });

        share.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "اشتراک گذاری");
                i.putExtra(Intent.EXTRA_TEXT, "https://hamedanmelk.ir/");
                startActivity(Intent.createChooser(i, "اشتراک گذاری"));
                return false;
            }
        });

        Objects.requireNonNull(about_preference).setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                controller.navigate(R.id.HelpFragment);
                return false;
            }
        });


        if (loggedInPreference != null)
            loggedInPreference.setExitButtonClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(getContext(),R.style.AlertDialogCustom);
                    adb.setTitle(getResources().getString(R.string.alert_dialog_logout_message));
                    adb.setIcon(getResources().getDrawable(R.drawable.ic_baseline_exit_to_app_24));
                    adb.setPositiveButton(getResources().getString(R.string.alert_dialog_ok), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences.Editor editor = Objects.requireNonNull(getActivity()).getSharedPreferences(getString(R.string.user_shared_preference), MODE_PRIVATE).edit();
                            editor.clear().apply();
                            loggedInPreference.setVisible(false);
                            user_favorites.setVisible(false);
                            user_lands.setVisible(false);
                            notLoggedInPreference.setVisible(true);
                        }
                    });
                    adb.setNegativeButton(getResources().getString(R.string.alert_dialog_cancel), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    adb.show();


                }
            });

        if (notLoggedInPreference != null)
            notLoggedInPreference.setLoginButtonClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    controller.navigate(R.id.userLogin);
                }
            });


    }

}