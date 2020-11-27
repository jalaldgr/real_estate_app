package ir.hamedanmelk.hamedanmelk.ui.newland;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.Objects;

import ir.hamedanmelk.hamedanmelk.R;

public class NewLandFragment extends Fragment {

    private NewLandViewModel newLandViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        newLandViewModel =
                ViewModelProviders.of(this).get(NewLandViewModel.class);
        //TODO shared preference :get value from  settings
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
//      final String sss =  prefs.getString("edit_text_notification_preference","d");
        View root = inflater.inflate(R.layout.fragment_new_land, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        SharedPreferences user_pref = Objects.requireNonNull(getActivity()).getSharedPreferences(getString(R.string.user_shared_preference), Context.MODE_PRIVATE);
        final NavController controller= Navigation.findNavController(Objects.requireNonNull(getActivity()),R.id.nav_host_fragment);
        if(!user_pref.contains("id")){
            controller.navigate(R.id.userLogin);
        }
        newLandViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}