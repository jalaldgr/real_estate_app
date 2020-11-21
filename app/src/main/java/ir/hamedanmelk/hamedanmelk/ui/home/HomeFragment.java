package ir.hamedanmelk.hamedanmelk.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.tools.ViewPagerAdapter;
import ir.hamedanmelk.hamedanmelk.models.Estate;
import ir.hamedanmelk.hamedanmelk.tools.home_frgmnt_horizontal_recycler_adaptor;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    RecyclerView myRecycle;
    home_frgmnt_horizontal_recycler_adaptor RHZAdaptor ;
    List<Estate> mylist = new ArrayList<>();
    Button salebtn;
    Button rentbtn;
    Button assignmentbtn;
    Button resalebtn;
    Button exchangebtn;
    Button agentbtn;
    Button servicesbtn;
    Button lawinstiutebtn;
    Button inquirybtn;
    Button morebtn;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
        final NavController controller= Navigation.findNavController(Objects.requireNonNull(getActivity()),R.id.nav_host_fragment);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });
        salebtn = (Button)root.findViewById(R.id.HomeFragmentSaleButton);
        rentbtn=(Button)root.findViewById(R.id.HomeFragmentRentButton);
        assignmentbtn=(Button)root.findViewById(R.id.HomeFragmentAssignmentButton);
        resalebtn=(Button)root.findViewById(R.id.HomeFragmentResaleButton);
        exchangebtn=(Button)root.findViewById(R.id.HomeFragmentExchangeButton);
        agentbtn=(Button)root.findViewById(R.id.HomeFragmentAgentButton);
        servicesbtn=(Button)root.findViewById(R.id.HomeFragmentServicesButton);
        lawinstiutebtn=(Button)root.findViewById(R.id.HomeFragmentLawInstiuteButton);
        inquirybtn=(Button)root.findViewById(R.id.HomeFragmentInquiryButton);
        morebtn=(Button)root.findViewById(R.id.HomeFragmentMoreButton);

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
                controller.navigate(R.id.resaleFragment);
            }
        });
        exchangebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.exchangeFragment);
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
                controller.navigate(R.id.servicesFragment);
            }
        });
        lawinstiutebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.lawInstiuteFragment);
            }
        });
        inquirybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.inquiryFragment);
            }
        });
        morebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.moreFragment);
            }
        });

        myRecycle = (RecyclerView) root.findViewById(R.id.HomeFrgmntHrzntlRcyclVw);
        mylist.add(new Estate("خانه کلنگی","همدان خضر","32000000"));
        mylist.add(new Estate("زمین کشاورزی","قهاوند","2500000000"));
        mylist.add(new Estate("خانه دربستی","خواجه رشید","56000000"));
        mylist.add(new Estate("آپارتمان","سعیدیه","245000000"));
        mylist.add(new Estate("اجاره ویلا","حیدره پشت کوه","250000"));
        mylist.add(new Estate("آپارتمان نقلی","پل گیشا","366000000"));
        mylist.add(new Estate("مغازه 73 متری","کوی جنت","5600000000"));
        RHZAdaptor = new home_frgmnt_horizontal_recycler_adaptor(mylist, this.getContext());
        RecyclerView.LayoutManager laymngr =  new LinearLayoutManager(this.getContext());
        myRecycle.setLayoutManager(laymngr);
        myRecycle.setAdapter(RHZAdaptor);

        ViewPager viewPager;
        viewPager = (ViewPager) root.findViewById(R.id.viewpager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getContext());
        viewPager.setAdapter(viewPagerAdapter);
        return root;
    }
}