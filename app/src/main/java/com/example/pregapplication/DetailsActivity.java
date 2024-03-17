package com.example.pregapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        WebView editWeb = findViewById(R.id.web);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        editWeb.loadUrl(url);
    }
}