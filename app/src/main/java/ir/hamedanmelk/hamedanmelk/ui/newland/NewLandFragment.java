package ir.hamedanmelk.hamedanmelk.ui.newland;


import android.content.Context;
import android.content.SharedPreferences;
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
import ir.hamedanmelk.hamedanmelk.tools.Constants;

public class NewLandFragment extends Fragment  {
    Button saleBtn;
    Button preSaleBtn;
    Button rentBtn;
    Button participationBtn;
    Button companyBtn;

    public NewLandFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_land, container, false);
        final NavController controller = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);


        SharedPreferences user_pref = Objects.requireNonNull(getActivity()).getSharedPreferences(getString(R.string.user_shared_preference), Context.MODE_PRIVATE);
        if(!user_pref.contains("id")) {

            controller.navigate(R.id.userLogin);

        }

        saleBtn = (Button)view.findViewById(R.id.NewLandFragmentSaleBtn);
        preSaleBtn =(Button)view.findViewById(R.id.NewLandFragmentPreSaleBtn);
        rentBtn = (Button)view.findViewById(R.id.NewLandFragmentRentBtn);
        participationBtn = (Button)view.findViewById(R.id.NewLandFragmentParticipationBtn);
        companyBtn = (Button)view.findViewById(R.id.newLandFragmentCompanyBtn);

        saleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.newLandSaleFragment);
            }
        });

        preSaleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.newLandPreSaleFragment);
            }
        });

        rentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.newLandRentFragment);
            }
        });

        participationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.newLandParticipationFragment);
            }
        });

        companyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.newCompanyFragment);
            }
        });
        return view;
    }
}
