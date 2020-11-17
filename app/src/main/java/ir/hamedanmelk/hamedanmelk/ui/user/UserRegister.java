package ir.hamedanmelk.hamedanmelk.ui.user;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Objects;

import ir.hamedanmelk.hamedanmelk.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserRegister#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserRegister extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserRegister() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserRegister.
     */
    // TODO: Rename and change types and number of parameters
    public static UserRegister newInstance(String param1, String param2) {
        UserRegister fragment = new UserRegister();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final NavController controller= Navigation.findNavController(Objects.requireNonNull(getActivity()),R.id.nav_host_fragment);
        View v = inflater.inflate(R.layout.fragment_user_register, container, false);
        Button register = (Button)v.findViewById(R.id.UserRegisterRegisterationButton);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.personalPage);
            }
        });
        return v;
    }
}