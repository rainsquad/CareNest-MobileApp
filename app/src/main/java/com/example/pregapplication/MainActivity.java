package com.example.pregapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pregapplication.fragment.CommunityFragment;
import com.example.pregapplication.fragment.HomeFragment;
import com.example.pregapplication.fragment.MainMenuFragment;
import com.example.pregapplication.fragment.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    CommunityFragment communityFragment = new CommunityFragment();

    HomeFragment homeFragment = new HomeFragment();
    SettingsFragment settingsFragment = new SettingsFragment();

    MainMenuFragment mainMenuFragment = new MainMenuFragment();
    FrameLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        layout = findViewById(R.id.container);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mainMenuFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                if(item.getItemId()==R.id.home)
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, mainMenuFragment).commit();
                }else if(item.getItemId()==R.id.community)
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, communityFragment).commit();
                }
                else if(item.getItemId()==R.id.settings) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, settingsFragment).commit();
                }
                return true;
            }
        });

    }
}