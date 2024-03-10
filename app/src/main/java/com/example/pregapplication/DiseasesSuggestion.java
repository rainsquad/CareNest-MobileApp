package com.example.pregapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
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
import okhttp3.Response;

public class DiseasesSuggestion extends Activity {
    Button diseasesAnalyzeButton;
    int[] checkboxValues;
    CheckBox[] checkboxes;
    String diseasesSuggestion;

    // Define the URL of the flask server
    //String serverUrl = "http://192.168.8.100:5000/getDiseases";
    String serverUrl = "http://10.0.2.2:5000/getDiseases";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diseases_suggestion);

        diseasesAnalyzeButton = findViewById(R.id.analyze_button);
        checkboxValues = new int[132]; // assuming you have 132 checkboxes
        checkboxes = new CheckBox[132]; // create an array of CheckBox objects

        // loop through the array and assign the references to the checkboxes
        for (int i = 0; i < checkboxes.length; i++) {
            // use the findViewById method to get the reference to the checkbox with the id "checkbox_i"
            checkboxes[i] = findViewById(getResources().getIdentifier("checkbox_" + (i+1), "id", getPackageName()));
        }

        diseasesAnalyzeButton.setOnClickListener(v -> {

            // loop through the checkboxes and check their checked state
            for (int i = 0; i < checkboxes.length; i++) {
                checkboxValues[i] = checkboxes[i].isChecked() ? 1 : 0; // assign 1 if checked, 0 otherwise
            }

            // create a FormBody.Builder object to build the form data
            FormBody.Builder builder = new FormBody.Builder();

            // loop through the checkbox values and add them to the builder, separated by commas
            for (int i = 0; i < checkboxValues.length; i++) {
                builder.add("type", String.valueOf(checkboxValues[i]));
                if (i < checkboxValues.length - 1) {
                    builder.add("type", ",");
                }
            }

            // create an OkHttpClient object to send the request
            OkHttpClient okHttpClient = new OkHttpClient();

            // build the form data
            FormBody formData = builder.build();

            // create a Request object with the URL and the form data
            Request request = new Request.Builder().url(serverUrl).post(formData).build();

            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(DiseasesSuggestion.this, "Network not found!", Toast.LENGTH_LONG).show();
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
                                diseasesSuggestion = response.body().string();
                                textView.setText(diseasesSuggestion);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        });
    }
}