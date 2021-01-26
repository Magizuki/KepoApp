package com.example.kepoapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.kepoapp.R;

public class MainMenuActivity extends AppCompatActivity {

    public final static String  Extra_User = "extra_user";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }
}