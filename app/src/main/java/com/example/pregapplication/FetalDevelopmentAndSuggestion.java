package com.example.pregapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FetalDevelopmentAndSuggestion extends Activity {

    // Declare the global variables
    EditText baselineValueEditText, accelerationsEditText, fetalMovementEditText, uterineContractionsEditText, lightDecelerationsEditText,
            severeDecelerationsEditText, prolonguedDecelerationsEditText, abnormalShortTermVariabilityEditText, meanValueOfShortTermVariabilityEditText,
            percentageOfTimeWithAbnormalLongTermVariabilityEditText, meanValueOfLongTermVariabilityEditText, histogramWidthEditText,
            histogramMinEditText, histogramMaxEditText, histogramNumberOfPeaksEditText, histogramNumberOfZeroesEditText, histogramModeEditText,
            histogramMeanEditText, histogramMedianEditText, histogramVarianceEditText, histogramTendencyEditText;
    Button fetalAnalyzeButton;
    String fetalSuggestions;


    // Define the URL of the flask server
    //String serverUrl = "http://192.168.8.100:5000/getFood";
    String serverUrl = "http://10.0.2.2:5000/getFood";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fetal_development_and_suggestion);

        baselineValueEditText = findViewById(R.id.baseline_value);
        accelerationsEditText = findViewById(R.id.accelerations);
        fetalMovementEditText = findViewById(R.id.fetal_movement);
        uterineContractionsEditText = findViewById(R.id.uterine_contractions);
        lightDecelerationsEditText = findViewById(R.id.light_decelerations);
        severeDecelerationsEditText = findViewById(R.id.severe_decelerations);
        prolonguedDecelerationsEditText = findViewById(R.id.prolongued_decelerations);
        abnormalShortTermVariabilityEditText = findViewById(R.id.abnormal_short_term_variability);
        meanValueOfShortTermVariabilityEditText = findViewById(R.id.mean_value_of_short_term_variability);
        percentageOfTimeWithAbnormalLongTermVariabilityEditText = findViewById(R.id.percentage_of_time_with_abnormal_long_term_variability);
        meanValueOfLongTermVariabilityEditText = findViewById(R.id.mean_value_of_long_term_variability);
        histogramWidthEditText = findViewById(R.id.histogram_width);
        histogramMinEditText = findViewById(R.id.histogram_min);
        histogramMaxEditText = findViewById(R.id.histogram_max);
        histogramNumberOfPeaksEditText = findViewById(R.id.histogram_number_of_peaks);
        histogramNumberOfZeroesEditText = findViewById(R.id.histogram_number_of_zeroes);
        histogramModeEditText = findViewById(R.id.histogram_mode);
        histogramMeanEditText = findViewById(R.id.histogram_mean);
        histogramMedianEditText = findViewById(R.id.histogram_median);
        histogramVarianceEditText = findViewById(R.id.histogram_variance);
        histogramTendencyEditText = findViewById(R.id.histogram_tendency);
        fetalAnalyzeButton = findViewById(R.id.analyze_button);

        // Set a click listener for the submit button
        fetalAnalyzeButton.setOnClickListener(v -> {

            // Get the user inputs from the edit texts
            String baselineValue = baselineValueEditText.getText().toString();
            String accelerations = accelerationsEditText.getText().toString();
            String fetalMovement = fetalMovementEditText.getText().toString();
            String uterineContractions = uterineContractionsEditText.getText().toString();
            String lightDecelerations = lightDecelerationsEditText.getText().toString();
            String severeDecelerations = severeDecelerationsEditText.getText().toString();
            String prolonguedDecelerations = prolonguedDecelerationsEditText.getText().toString();
            String abnormalShortTermVariability = abnormalShortTermVariabilityEditText.getText().toString();
            String meanValueOfShortTermVariability = meanValueOfShortTermVariabilityEditText.getText().toString();
            String percentageOfTimeWithAbnormalLongTermVariability = percentageOfTimeWithAbnormalLongTermVariabilityEditText.getText().toString();
            String meanValueOfLongTermVariability = meanValueOfLongTermVariabilityEditText.getText().toString();
            String histogramWidth = histogramWidthEditText.getText().toString();
            String histogramMin = histogramMinEditText.getText().toString();
            String histogramMax = histogramMaxEditText.getText().toString();
            String histogramNumberOfPeaks = histogramNumberOfPeaksEditText.getText().toString();
            String histogramNumberOfZeroes = histogramNumberOfZeroesEditText.getText().toString();
            String histogramMode = histogramModeEditText.getText().toString();
            String histogramMean = histogramMeanEditText.getText().toString();
            String histogramMedian = histogramMedianEditText.getText().toString();
            String histogramVariance = histogramVarianceEditText.getText().toString();
            String histogramTendency = histogramTendencyEditText.getText().toString();

            // Validate the user inputs
            if (
                    baselineValue.isEmpty() && accelerations.isEmpty() && fetalMovement.isEmpty() && uterineContractions.isEmpty() &&
                    lightDecelerations.isEmpty() && severeDecelerations.isEmpty() && prolonguedDecelerations.isEmpty() && abnormalShortTermVariability.isEmpty() &&
                    meanValueOfShortTermVariability.isEmpty() && percentageOfTimeWithAbnormalLongTermVariability.isEmpty() && meanValueOfLongTermVariability.isEmpty() &&
                    histogramWidth.isEmpty() && histogramMin.isEmpty() && histogramMax.isEmpty() && histogramNumberOfPeaks.isEmpty() && histogramNumberOfZeroes.isEmpty() &&
                    histogramMode.isEmpty() && histogramMean.isEmpty() && histogramMedian.isEmpty() && histogramVariance.isEmpty() && histogramTendency.isEmpty()
            ) {

                // If any input is empty, show a toast message
                Toast.makeText(FetalDevelopmentAndSuggestion.this, "Please enter required inputs", Toast.LENGTH_SHORT).show();

            } else {
                OkHttpClient okHttpClient = new OkHttpClient();

                RequestBody formbody = new FormBody.Builder()
                        .add("baseline_value", baselineValue)
                        .add("accelerations", accelerations)
                        .add("fetal_movement", fetalMovement)
                        .add("uterine_contractions", uterineContractions)
                        .add("light_decelerations", lightDecelerations)
                        .add("severe_decelerations", severeDecelerations)
                        .add("prolongued_decelerations", prolonguedDecelerations)
                        .add("abnormal_short_term_variability", abnormalShortTermVariability)
                        .add("mean_value_of_short_term_variability", meanValueOfShortTermVariability)
                        .add("percentage_of_time_with_abnormal_long_term_variability", percentageOfTimeWithAbnormalLongTermVariability)
                        .add("mean_value_of_long_term_variability", meanValueOfLongTermVariability)
                        .add("histogram_width", histogramWidth)
                        .add("histogram_min", histogramMin)
                        .add("histogram_max", histogramMax)
                        .add("histogram_number_of_peaks", histogramNumberOfPeaks)
                        .add("histogram_number_of_zeroes", histogramNumberOfZeroes)
                        .add("histogram_mode", histogramMode)
                        .add("histogram_mean", histogramMean)
                        .add("histogram_median", histogramMedian)
                        .add("histogram_variance", histogramVariance)
                        .add("histogram_tendency", histogramTendency)
                        .build();

                Request request = new Request.Builder().url(serverUrl).post(formbody).build();

                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(FetalDevelopmentAndSuggestion.this, "Network not found!", Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView textView = findViewById(R.id.output);
                                try {
                                    fetalSuggestions = response.body().string();
                                    textView.setText(fetalSuggestions);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });
            }
        });
    }
}