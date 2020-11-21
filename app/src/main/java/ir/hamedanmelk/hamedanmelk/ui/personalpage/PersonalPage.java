package ir.hamedanmelk.hamedanmelk.ui.personalpage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.preference.PreferenceManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.tools.Constants;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonalPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalPage extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PersonalPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PersonalPage.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonalPage newInstance(String param1, String param2) {
        PersonalPage fragment = new PersonalPage();
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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_personal_page, container, false);
        SharedPreferences user_pref = getActivity().getSharedPreferences(getString(R.string.user_shared_preference), Context.MODE_PRIVATE);
        final NavController controller=Navigation.findNavController(Objects.requireNonNull(getActivity()),R.id.nav_host_fragment);
        String[] user_model_fields = Constants.USER_MODEL_FIELDS;

        Button exitbtn = (Button)v.findViewById(R.id.PersoanlPageExitButton);
        TextView nametxt=(TextView)v.findViewById(R.id.PersonalPageNametxt);
        TextView phonetxt=(TextView)v.findViewById(R.id.PersonalPagePhonetxt);
        String fullnamestr=user_pref.getString(user_model_fields[2],"")+" "+user_pref.getString(user_model_fields[3],"");
        String phonestr=user_pref.getString(user_model_fields[1],"");
        nametxt.setText(fullnamestr);
        phonetxt.setText(phonestr);
        exitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = Objects.requireNonNull(getActivity()).getSharedPreferences(getString(R.string.user_shared_preference), MODE_PRIVATE).edit();
                editor.clear().apply();
                controller.navigate(R.id.userLogin);

            }
        });
        return v;
    }
}