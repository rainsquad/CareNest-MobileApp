package com.example.pregapplication.fragment;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.pregapplication.DiseasesSuggestionFragment;
import com.example.pregapplication.EmotionDetectionFragment;
import com.example.pregapplication.FetalDevelopmentAndSuggestionFragment;
import com.example.pregapplication.FoodSuggestionFragment;
import com.example.pregapplication.R;

public class HomeFragment extends Fragment {
    CardView foodSuggest, emotionDetection, diseaseSuggest, fetalHealth;
    FoodSuggestionFragment foodSuggestion = new FoodSuggestionFragment();
    EmotionDetectionFragment emotionDetectionFragment = new EmotionDetectionFragment();
    FetalDevelopmentAndSuggestionFragment fetalDevelopmentAndSuggestionFragment = new FetalDevelopmentAndSuggestionFragment();
    DiseasesSuggestionFragment diseasesSuggestionFragment = new DiseasesSuggestionFragment();
    FrameLayout layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        foodSuggest = view.findViewById(R.id.crdFoodSuggest);
        emotionDetection = view.findViewById(R.id.crdRealTimeStress);
        diseaseSuggest = view.findViewById(R.id.crdDiseaseSuggest);
        fetalHealth = view.findViewById(R.id.crdFetalHealth);
        layout = view.findViewById(R.id.container);

        foodSuggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, foodSuggestion)
                       .addToBackStack(null)  // Add to back stack if needed
                       .commit();
            }
        });
        emotionDetection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, emotionDetectionFragment)
                        .addToBackStack(null)  // Add to back stack if needed
                        .commit();
            }
        });
        diseaseSuggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, diseasesSuggestionFragment)
                        .addToBackStack(null)  // Add to back stack if needed
                        .commit();
            }
        });
        fetalHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fetalDevelopmentAndSuggestionFragment)
                        .addToBackStack(null)  // Add to back stack if needed
                        .commit();
            }
        });
        return view;
    }
}