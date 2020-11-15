package ir.hamedanmelk.hamedanmelk.ui.home;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.ViewPagerAdapter;
import ir.hamedanmelk.hamedanmelk.models.Estate;
import ir.hamedanmelk.hamedanmelk.tools.home_frgmnt_horizontal_recycler_adaptor;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    RecyclerView myRecycle;
    home_frgmnt_horizontal_recycler_adaptor RHZAdaptor ;
    List<Estate> mylist = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
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

//          ViewPager viewPager;
//        viewPager = (ViewPager) root.findViewById(R.id.viewpager);
//        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getContext());
//        viewPager.setAdapter(viewPagerAdapter);
        return root;
    }
}