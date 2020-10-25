package ir.hamedanmelk.hamedanmelk.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ir.hamedanmelk.hamedanmelk.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NoInternetErrorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoInternetErrorFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button retrybutton;

    public NoInternetErrorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NoInternetErrorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NoInternetErrorFragment newInstance(String param1, String param2) {
        NoInternetErrorFragment fragment = new NoInternetErrorFragment();
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
        View v;
        v=inflater.inflate(R.layout.fragment_no_internet_error, container, false);
        retrybutton = v.findViewById(R.id.no_internet_fragment_button_retry);
        retrybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController controller = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);


            }
        });
        return v;
    }
}