package com.example.pregapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.anychart.AnyChartView;
import com.anychart.AnyChart;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.enums.Position;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.example.pregapplication.classes.Services;

public class FetalDevelopmentAndSuggestionFragment extends Fragment {
    // Get the URL of the flask server
    String serverUrl = Services.ipAddress + "/getFetalDevelopment";

    // Declare the global variables
    EditText baselineValueEditText, accelerationsEditText, fetalMovementEditText, uterineContractionsEditText, lightDecelerationsEditText,
            severeDecelerationsEditText, prolonguedDecelerationsEditText, abnormalShortTermVariabilityEditText, meanValueOfShortTermVariabilityEditText,
            percentageOfTimeWithAbnormalLongTermVariabilityEditText, meanValueOfLongTermVariabilityEditText, histogramWidthEditText,
            histogramMinEditText, histogramMaxEditText, histogramNumberOfPeaksEditText, histogramNumberOfZeroesEditText, histogramModeEditText,
            histogramMeanEditText, histogramMedianEditText, histogramVarianceEditText, histogramTendencyEditText;
    Button fetalAnalyzeButton;
    String fetalSuggestions;
    TextView suggetionsProb;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fetal_development_and_suggestion, container, false);

        baselineValueEditText = v.findViewById(R.id.baseline_value);
        accelerationsEditText = v.findViewById(R.id.accelerations);
        fetalMovementEditText = v.findViewById(R.id.fetal_movement);
        uterineContractionsEditText = v.findViewById(R.id.uterine_contractions);
        lightDecelerationsEditText = v.findViewById(R.id.light_decelerations);
        severeDecelerationsEditText = v.findViewById(R.id.severe_decelerations);
        prolonguedDecelerationsEditText = v.findViewById(R.id.prolongued_decelerations);
        abnormalShortTermVariabilityEditText = v.findViewById(R.id.abnormal_short_term_variability);
        meanValueOfShortTermVariabilityEditText = v.findViewById(R.id.mean_value_of_short_term_variability);
        percentageOfTimeWithAbnormalLongTermVariabilityEditText = v.findViewById(R.id.percentage_of_time_with_abnormal_long_term_variability);
        meanValueOfLongTermVariabilityEditText = v.findViewById(R.id.mean_value_of_long_term_variability);
        histogramWidthEditText = v.findViewById(R.id.histogram_width);
        histogramMinEditText = v.findViewById(R.id.histogram_min);
        histogramMaxEditText = v.findViewById(R.id.histogram_max);
        histogramNumberOfPeaksEditText = v.findViewById(R.id.histogram_number_of_peaks);
        histogramNumberOfZeroesEditText = v.findViewById(R.id.histogram_number_of_zeroes);
        histogramModeEditText = v.findViewById(R.id.histogram_mode);
        histogramMeanEditText = v.findViewById(R.id.histogram_mean);
        histogramMedianEditText = v.findViewById(R.id.histogram_median);
        histogramVarianceEditText = v.findViewById(R.id.histogram_variance);
        histogramTendencyEditText = v.findViewById(R.id.histogram_tendency);
        fetalAnalyzeButton = v.findViewById(R.id.analyze_button);
        
//        suggetionsProb = v.findViewById(R.id.suggetions_prob);

        // Set a click listener for the submit button
        fetalAnalyzeButton.setOnClickListener(view -> {

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
                    baselineValue.isEmpty() || accelerations.isEmpty() || fetalMovement.isEmpty() || uterineContractions.isEmpty() ||
                    lightDecelerations.isEmpty() || severeDecelerations.isEmpty() || prolonguedDecelerations.isEmpty() || abnormalShortTermVariability.isEmpty() ||
                    meanValueOfShortTermVariability.isEmpty() || percentageOfTimeWithAbnormalLongTermVariability.isEmpty() || meanValueOfLongTermVariability.isEmpty() ||
                    histogramWidth.isEmpty() || histogramMin.isEmpty() || histogramMax.isEmpty() || histogramNumberOfPeaks.isEmpty() || histogramNumberOfZeroes.isEmpty() ||
                    histogramMode.isEmpty() || histogramMean.isEmpty() || histogramMedian.isEmpty() || histogramVariance.isEmpty() || histogramTendency.isEmpty()
            ) {

                // If any input is empty, show a toast message
                Toast.makeText(getContext(), "Please enter all the required inputs", Toast.LENGTH_SHORT).show();

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
                        requireActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(), "Network not found!", Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String responseBody = response.body().string();
                        try {
                            // Parse the response body as a JSON object
//                            JSONObject jsonObject = new JSONObject(responseBody);
//                            JSONArray b64_plot_data = jsonObject.getJSONArray("b64_plot");
//
//                            String finalString = b64_plot_data.toString();

                            // Parse the response body as a JSON object
                            JSONObject jsonObject = new JSONObject(responseBody);

                            // Get the base64 encoded string
                            String b64_plot_data = jsonObject.getString("b64_plot");



                            // Remove the "data:image/png;base64," part if it's included in the response
//                            b64_plot_data = b64_plot_data.replace("data:image/png;base64,", "").replace("data:image/jpeg;base64,", "");

                            // Decode the base64 string
//                            byte[] decodedString = Base64.decode(b64_plot_data, Base64.DEFAULT);

                            // Convert decoded byte array into Bitmap
//                            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                            // Set the Bitmap to ImageView
//                            ImageView imageView = (ImageView) getView().findViewById(R.id.plot); // Replace with your ImageView ID
//                            imageView.setImageBitmap(decodedByte);



                            requireActivity().runOnUiThread(() -> {
//                                suggetionsProb.setText(finalString);
                                Toast.makeText(getContext(), ""+b64_plot_data.length(), Toast.LENGTH_SHORT).show();
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        return v;
    }
}