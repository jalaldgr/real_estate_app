package ir.hamedanmelk.hamedanmelk.ui.personalpage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.ui.single.GetLandEnergyFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScrollingMyHamedanMelkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScrollingMyHamedanMelkFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ScrollingMyHamedanMelkFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScrollingMyHamedanMelkFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ScrollingMyHamedanMelkFragment newInstance(String param1, String param2) {
        ScrollingMyHamedanMelkFragment fragment = new ScrollingMyHamedanMelkFragment();
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
        View view = inflater.inflate(R.layout.fragment_scrolling_my_hamedan_melk, container, false);
//        FragmentManager childFragMan = getChildFragmentManager();
//        FragmentTransaction childFragTrans = childFragMan.beginTransaction();
//        MyHamedanMelkFragment fragB = new MyHamedanMelkFragment ();
//        childFragTrans.add(R.id.MyHamedanMelkFrameLyt, fragB);
//        childFragTrans.commit();
        return view;
    }
}