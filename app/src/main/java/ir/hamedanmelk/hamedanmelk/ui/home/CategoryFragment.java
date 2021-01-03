package ir.hamedanmelk.hamedanmelk.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import ir.hamedanmelk.hamedanmelk.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ImageButton salebtn;
    ImageButton rentbtn;
    ImageButton assignmentbtn;
    ImageButton resalebtn;
    ImageButton agentbtn;
    ImageButton servicesbtn;
    ImageButton officebtn;
    ImageButton lawyerbtn;
    ImageButton newsbtn;
    ImageButton companybtn;

    public CategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();
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
        View root = inflater.inflate(R.layout.fragment_category, container, false);


        salebtn = (ImageButton)root.findViewById(R.id.CategoryFragmentSaleIBtn);
        rentbtn=(ImageButton)root.findViewById(R.id.CategoryFragmentRentIBtn);
        assignmentbtn=(ImageButton)root.findViewById(R.id.CategoryFragmentParticipationIBtn);
        resalebtn=(ImageButton)root.findViewById(R.id.CategoryFragmentPreSaleIBtn);
        newsbtn=(ImageButton)root.findViewById(R.id.CategoryFragmentNewsIBtn);
        agentbtn=(ImageButton)root.findViewById(R.id.CategoryFragmentAgentIBtn);
        servicesbtn=(ImageButton)root.findViewById(R.id.CategoryFragmentServicesIBtn);
        officebtn =(ImageButton)root.findViewById(R.id.CategoryFragmentOfficeIBtn);
        lawyerbtn =(ImageButton)root.findViewById(R.id.CategoryFragmentLawyersIBtn);
        companybtn=(ImageButton)root.findViewById(R.id.CategoryFragmentCompanyIBtn);


        final NavController controller = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);

        salebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.saleFragment);
            }
        });
        rentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.rentFragment);
            }
        });
        assignmentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.assingmentFragment);
            }
        });
        resalebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.presaleFragment);
            }
        });

        newsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.newsFragment);
            }
        });
        agentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.agentFragment);
            }
        });
        servicesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.linksFragment);
            }
        });
        officebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.officesFragment);
            }
        });
        lawyerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.lawInstiuteFragment);
            }
        });
        companybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.servicesFragment);
            }
        });


        return root;
    }
}