package ir.hamedanmelk.hamedanmelk.ui.category.agencis;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutPagerAdaptor extends FragmentStatePagerAdapter {



        private final List<Fragment> frgList = new ArrayList<>();
        private final List<String> titleList = new ArrayList<>();

        public TabLayoutPagerAdaptor(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            return frgList.get(position);
        }

        @Override
        public int getCount() {
            return titleList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }

        public void addFragments (Fragment frg, String title) {
//            frg.setEnterTransition(R.anim.slide_in);
//            frg.setExitTransition(R.anim.fade_out);
//                    R.anim.slide_in,  // enter
//                    R.anim.fade_out,  // exit
//                    R.anim.fade_in,   // popEnter
//                    R.anim.slide_out  // popExit
//            );
            frgList.add(frg);
            titleList.add(title);
        }



}
