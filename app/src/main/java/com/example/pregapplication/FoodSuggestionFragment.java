package com.example.pregapplication;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.pregapplication.classes.Services;

public class FoodSuggestionFragment extends Fragment {
    // Get the URL of the flask server
    String serverUrl = Services.ipAddress + "/getFood";

    // Declare the global variables
    private EditText weightEditText, heightEditText, ageEditText, weeksEditText;
    private Spinner activitySpinner, gainSpinner;
    private Button foodAnalyzeButton;
    private TextView outputTextView;
    private String foodSuggestions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_suggestion, container, false);

//        if (android.os.Build.VERSION.SDK_INT > 9) {
//            StrictMode.ThreadPolicy gfgPolicy =
//                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(gfgPolicy);
//        }

        weightEditText = view.findViewById(R.id.weight_edit_text);
        heightEditText = view.findViewById(R.id.height_edit_text);
        ageEditText = view.findViewById(R.id.age_edit_text);
        weeksEditText = view.findViewById(R.id.weeks_edit_text);
        activitySpinner = view.findViewById(R.id.activity_spinner);
        gainSpinner = view.findViewById(R.id.gain_spinner);
        foodAnalyzeButton = view.findViewById(R.id.analyze_button);
        outputTextView = view.findViewById(R.id.output);

        // Create an array adapter for the spinners
        ArrayAdapter<CharSequence> activityAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.activity_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> gainAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.gain_array, android.R.layout.simple_spinner_item);

        // Set the layout for the spinner dropdown items
        activityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gainAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the adapter for the spinners
        activitySpinner.setAdapter(activityAdapter);
        gainSpinner.setAdapter(gainAdapter);

        // Set a click listener for the submit button
        foodAnalyzeButton.setOnClickListener(v -> {
            // Get the user inputs from the edit texts and the spinners
            String weight = weightEditText.getText().toString();
            String height = heightEditText.getText().toString();
            String age = ageEditText.getText().toString();
            String weeks = weeksEditText.getText().toString();
            String activity = activitySpinner.getSelectedItem().toString();
            String gain = gainSpinner.getSelectedItem().toString();

            // Validate the user inputs
            if (weight.isEmpty() || height.isEmpty() || age.isEmpty()) {
                // If any input is empty, show a toast message
                Toast.makeText(requireContext(), "Please enter all the required inputs", Toast.LENGTH_SHORT).show();
            } else {
                OkHttpClient okHttpClient = new OkHttpClient();

                RequestBody formbody = new FormBody.Builder()
                        .add("weight", weight)
                        .add("height", height)
                        .add("age", age)
                        .add("weeks", weeks)
                        .add("activity", activity)
                        .add("gain", gain)
                        .build();

                Request request = new Request.Builder().url(serverUrl).post(formbody).build();

                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        requireActivity().runOnUiThread(() ->
                                Toast.makeText(requireContext(), "Network not found!", Toast.LENGTH_LONG).show());
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        foodSuggestions = response.body().string();
                        requireActivity().runOnUiThread(() -> {
                            outputTextView.setText(foodSuggestions);
                        });
                    }
                });
            }
        });
        return view;
    }
}