package com.example.pregapplication.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.example.pregapplication.Adapters.MyPageAdapter;
import com.example.pregapplication.R;
import com.google.android.material.tabs.TabLayout;



    public class CommunityFragment extends Fragment {

        private TabLayout tabLayout;
        private ViewPager viewPager;
        private MyPageAdapter adapter;

        public CommunityFragment() {
            // Required empty public constructor
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View rootView = inflater.inflate(R.layout.fragment_community, container, false);

            viewPager = rootView.findViewById(R.id.viewPager);
            tabLayout = rootView.findViewById(R.id.tablayout);

            adapter = new MyPageAdapter(getChildFragmentManager());
            viewPager.setAdapter(adapter);

            tabLayout.setupWithViewPager(viewPager);
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

            // for top bar of activity
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            ActionBar bar = activity.getSupportActionBar();
            if (bar != null) {
                bar.setTitle("News list");
                bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("blue")));
            }

            return rootView;
        }
    }
