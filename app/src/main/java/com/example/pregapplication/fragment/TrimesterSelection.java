package com.example.pregapplication.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.pregapplication.R;

public class TrimesterSelection extends Fragment {

    Button first, second, third;

    FirstTrimesterFragment firstTrimesterFragment = new FirstTrimesterFragment();

    SecondTrimesterFragment secondTrimesterFragment = new SecondTrimesterFragment();

    ThirdTrimesterFragment thirdTrimesterFragment = new ThirdTrimesterFragment();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_trimester_selection, container, false);
        first = v.findViewById(R.id.txtTri1);
        second = v.findViewById(R.id.txtTri2);
        third = v.findViewById(R.id.txtTri3);



        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.enter_animation, R.anim.exit_animation, R.anim.enter_animation, R.anim.exit_animation);
                // Replace the current fragment with the emotionDetectionFragment
                transaction.replace(R.id.container, firstTrimesterFragment).commit();
            }
        });
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.enter_animation, R.anim.exit_animation, R.anim.enter_animation, R.anim.exit_animation);
                // Replace the current fragment with the emotionDetectionFragment
                transaction.replace(R.id.container, secondTrimesterFragment).commit();
            }
        });
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.enter_animation, R.anim.exit_animation, R.anim.enter_animation, R.anim.exit_animation);
                // Replace the current fragment with the emotionDetectionFragment
                transaction.replace(R.id.container, thirdTrimesterFragment).commit();
            }
        });
        return v;
    }
}