package com.example.pregapplication.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.pregapplication.DiseasesSuggestionFragment;
import com.example.pregapplication.EmotionDetectionFragment;
import com.example.pregapplication.FetalGrowthFragment;
import com.example.pregapplication.FoodSuggestionFragment;
import com.example.pregapplication.QuestionnaireActivity;
import com.example.pregapplication.R;
import com.google.android.material.navigation.NavigationBarView;

public class MainMenuFragment extends Fragment {


    FoodSuggestionFragment foodSuggestionFragment = new FoodSuggestionFragment();

    DiseasesSuggestionFragment diseasesSuggestionFragment = new DiseasesSuggestionFragment();

    FetalGrowthFragment fetalGrowthFragment = new FetalGrowthFragment();
    EmotionDetectionFragment emotionDetectionFragment = new EmotionDetectionFragment();
    TrimesterSelection trimesterSelection = new TrimesterSelection();
    LinearLayout clickEPDS, clickFood, clickEmotions, clickFetal, clickDiseases;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main_menu, container, false);

        clickEPDS = v.findViewById(R.id.clickEPDS);
        clickFood = v.findViewById(R.id.clickFoods);
        clickEmotions = v.findViewById(R.id.clickEmotions);
        clickFetal = v.findViewById(R.id.clickFetal);
        clickDiseases = v.findViewById(R.id.clickDiseases);

        clickEPDS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            Intent i = new Intent(getActivity(),QuestionnaireActivity.class);
            startActivity(i);
            }
        });
        clickFetal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.enter_animation, R.anim.exit_animation, R.anim.enter_animation, R.anim.exit_animation);
                // Replace the current fragment with the emotionDetectionFragment
                transaction.replace(R.id.container, fetalGrowthFragment).commit();
            }
        });

        clickDiseases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.enter_animation, R.anim.exit_animation, R.anim.enter_animation, R.anim.exit_animation);
                // Replace the current fragment with the emotionDetectionFragment
                transaction.replace(R.id.container, trimesterSelection).commit();
            }
        });


        clickFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.enter_animation, R.anim.exit_animation, R.anim.enter_animation, R.anim.exit_animation);
                // Replace the current fragment with the emotionDetectionFragment
                transaction.replace(R.id.container, foodSuggestionFragment).commit();
            }
        });
        return v;
    }
}