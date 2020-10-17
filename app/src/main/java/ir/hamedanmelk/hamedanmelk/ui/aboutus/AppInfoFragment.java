package ir.hamedanmelk.hamedanmelk.ui.aboutus;

import android.app.ActionBar;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.icu.util.VersionInfo;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.ui.AppBarConfiguration;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

import ir.hamedanmelk.hamedanmelk.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppInfoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView textView;
    String version;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AppInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AppInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AppInfoFragment newInstance(String param1, String param2) {
        AppInfoFragment fragment = new AppInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        BottomNavigationView navBar = getActivity().findViewById(R.id.nav_view);
        navBar.setVisibility(View.GONE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_app_info, container, false);
        version = getResources().getString(R.string.aboutus_app_info_version)+" ";
        try {
            PackageInfo pInfo = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0);
            version += pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        textView = (TextView)v.findViewById(R.id.AppInfo_Version_textView);
        textView.setText(version);

        return  v;
    }
}