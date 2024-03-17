package com.example.pregapplication.Adapters;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.pregapplication.fragment.fragment;

public class MyPageAdapter extends FragmentStatePagerAdapter  {



        public MyPageAdapter(FragmentManager fm) {super(fm);}

        @Override
        public Fragment getItem(int i) {
            fragment frag = new fragment(i);
            return frag; }

        @Override
        public int getCount() {return 10;}

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            if(position == 0)
                return "VICE-News";
            else if(position == 1)
                return "Ary News";
            else if(position ==2)
                return "BBC News";
            else
                return null;
        }
}

