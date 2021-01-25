package com.example.kepoapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;


import com.example.kepoapp.R;

public class MainActivity extends AppCompatActivity {

    //Splash Duration
    private final int SPLASH_DURATION = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);

        //Splash Initialization
        //Splash duration in millisecond
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LoginRegisterActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DURATION);


    }
}