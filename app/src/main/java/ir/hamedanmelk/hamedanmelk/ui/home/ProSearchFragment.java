package ir.hamedanmelk.hamedanmelk.ui.home;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.micro.LandStateTypeModel;
import ir.hamedanmelk.hamedanmelk.models.micro.LandTypeModel;
import ir.hamedanmelk.hamedanmelk.tools.Constants;
import ir.hamedanmelk.hamedanmelk.tools.MYSQlDBHelper;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProSearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProSearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "ProSearch";
    MYSQlDBHelper dbHelper;
    Spinner landStateSpnr;
    Spinner landTypeSpnr;
    EditText fromYearTxt;
    EditText toYearTxt;
    EditText fromTotalPriceTxt;
    EditText toTotalPriceTxt;
    EditText fromFoundationTxt;
    EditText toFoundationTxt;
    EditText fromMortgageTxt;
    EditText toMortgageTxt;
    EditText fromRentPriceTxt;
    EditText toRentPriceTxt;
    Button    submitBtn;
    LinearLayout totalPriceLyt;
    LinearLayout mortgagePriceLyt;
    LinearLayout rentPriceLyt;

    ArrayList<LandTypeModel> landTypeModels;
    List<String> landTypeTitles= new ArrayList<String>();
    List<String> landTypeIDs= new ArrayList<String>();
    ArrayAdapter<String> landTypeAdapter ;

    ArrayList<LandStateTypeModel> landStateTypeModels;
    List<String> landStateTitles= new ArrayList<String>();
    List<String> landStateIDs= new ArrayList<String>();
    ArrayAdapter<String> landStateAdapter ;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    SharedPreferences.Editor editor;

    public ProSearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProSearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProSearchFragment newInstance(String param1, String param2) {
        ProSearchFragment fragment = new ProSearchFragment();
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
        dbHelper = new MYSQlDBHelper(getContext());
        editor = Objects.requireNonNull(getActivity()).getSharedPreferences(getString(R.string.pro_filter_pref), MODE_PRIVATE).edit();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pro_search, container, false);
        final NavController controller= Navigation.findNavController(Objects.requireNonNull(getActivity()),R.id.nav_host_fragment);

        landTypeSpnr = (Spinner)view.findViewById(R.id.ProSearchFragmentLandTypeSpnr);
        landStateSpnr = (Spinner)view.findViewById(R.id.ProSearchFragmentLandStateIDSpnr);
        fromYearTxt = (EditText)view.findViewById(R.id.ProSearchFragmentFromBuildingYearTxt);
        toYearTxt =(EditText)view.findViewById(R.id.ProSearchFragmentToBuildingYearTxt);
        fromTotalPriceTxt = (EditText)view.findViewById(R.id.ProSearchFragmentFromSaleTotalTxt);
        toTotalPriceTxt=(EditText)view.findViewById(R.id.ProSearchFragmentToSaleTotalTxt);
        fromFoundationTxt = (EditText)view.findViewById(R.id.ProSearchFragmentFromFoundationSpaceTxt);
        toFoundationTxt = (EditText)view.findViewById(R.id.ProSearchFragmentToFoundationSpaceTxt);
        fromMortgageTxt = (EditText)view.findViewById(R.id.ProSearchFragmentFromMortgagePriceTxt);
        toMortgageTxt = (EditText)view.findViewById(R.id.ProSearchFragmentToMortgagePriceTxt);
        fromRentPriceTxt = (EditText)view.findViewById(R.id.ProSearchFragmentFromRentPriceTxt);
        toRentPriceTxt = (EditText)view.findViewById(R.id.ProSearchFragmentToRentPriceTxt);
        submitBtn = (Button)view.findViewById(R.id.ProSearchFragmentSubmitbtn);

        totalPriceLyt = (LinearLayout)view.findViewById(R.id.ProSearchFragmentTotalPriceLyt);
        mortgagePriceLyt = (LinearLayout)view.findViewById(R.id.ProSearchFragmentMortgagePriceLyt);
        rentPriceLyt = (LinearLayout)view.findViewById(R.id.ProSearchFragmentRentPriceLyt);


        ////////////////////// land Type Spinner////////////////////////////////
        landTypeModels = dbHelper.GetLandTypeList();
        for (LandTypeModel Item : landTypeModels) {
            landTypeTitles.add(Item.getTitle());
            landTypeIDs.add(Item.getId());
        }
        landTypeAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, landTypeTitles);
        landTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        landTypeSpnr.setAdapter(landTypeAdapter);
        landTypeSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                requestNewModel.setLandTypeID(landTypeIDs.get(i));
                editor.putString("LandTypeID",landTypeIDs.get(i));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        ////////////////////// land State Spinner////////////////////////////////
        landStateTypeModels = dbHelper.GetLandStateList();
        for (LandStateTypeModel Item : landStateTypeModels) {
            landStateTitles.add(Item.getTitle());
            landStateIDs.add(Item.getId());
        }
        landStateAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, landStateTitles);
        landStateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        landStateSpnr.setAdapter(landStateAdapter);
        landStateSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                requestNewModel.setLandTypeID(landTypeIDs.get(i));
                editor.putString("LandStateID",landStateIDs.get(i));
                if(landStateIDs.get(i).equals("2")){
                    totalPriceLyt.setVisibility(View.GONE);
                    mortgagePriceLyt.setVisibility(View.VISIBLE);
                    rentPriceLyt.setVisibility(View.VISIBLE);
                }
                else {
                    totalPriceLyt.setVisibility(View.VISIBLE);
                    mortgagePriceLyt.setVisibility(View.GONE);
                    rentPriceLyt.setVisibility(View.GONE);

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor.putString("FromBuildingYear",fromYearTxt.getText().toString());
                editor.putString("ToBuildingYear",toYearTxt.getText().toString());
                editor.putString("FromSaleTotalPrice",fromTotalPriceTxt.getText().toString());
                editor.putString("ToSaleTotalPrice",toTotalPriceTxt.getText().toString());
                editor.putString("FromFoundationSpace",fromFoundationTxt.getText().toString());
                editor.putString("ToFoundationSpace",toFoundationTxt.getText().toString());
                editor.putString("FromMortgageTotalPrice",fromMortgageTxt.getText().toString());
                editor.putString("ToMortgageTotalPrice",toMortgageTxt.getText().toString());
                editor.putString("FromRentTotalPrice",fromRentPriceTxt.getText().toString());
                editor.putString("ToRentTotalPrice",toRentPriceTxt.getText().toString());
                editor.apply();
                editor.commit();
                controller.navigateUp();
            }
        });

        return view;
    }
}