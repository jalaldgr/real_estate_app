package ir.hamedanmelk.hamedanmelk.ui.category.agencis;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.ui.category.LawyersFragment;
import ir.hamedanmelk.hamedanmelk.ui.office.OfficesFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TablayoutLawyersOfficesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TablayoutLawyersOfficesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TabLayout myTabLayout;
     static ViewPager myViewPager;
     static TabLayoutPagerAdaptor myAdaptor;

    public TablayoutLawyersOfficesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TablayoutLawyersOffices.
     */
    // TODO: Rename and change types and number of parameters
    public static TablayoutLawyersOfficesFragment newInstance(String param1, String param2) {
        TablayoutLawyersOfficesFragment fragment = new TablayoutLawyersOfficesFragment();
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
        View view = inflater.inflate(R.layout.fragment_tablayout_lawyer_office, container, false);

        myTabLayout = (TabLayout) view.findViewById(R.id.lawyer_office_tab_layout);
        myViewPager = (ViewPager) view.findViewById(R.id.lawyer_office_viewpager);
        myAdaptor = new TabLayoutPagerAdaptor(((AppCompatActivity)getActivity()).getSupportFragmentManager());

        myAdaptor.addFragments(new LawyersFragment(), "وکلای حقوقی");
        myAdaptor.addFragments(new OfficesFragment(), "دفاتر اسناد رسمی");

        myViewPager.setAdapter(myAdaptor);

        myViewPager.setOffscreenPageLimit(2);

//        myTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(myViewPager){
//
//        });.

        myViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(myTabLayout));
        myTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                myViewPager.setCurrentItem(tab.getPosition());
                Log.i("TAG", "onTabSelected: " + tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.i("hhhhh", "onTabUnselected: " + tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.i("hhhhh", "onTabReselected: " + tab.getPosition());
            }
        });


//        myTabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
//        myTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        myTabLayout.setupWithViewPager(myViewPager);

        return view;
    }
}