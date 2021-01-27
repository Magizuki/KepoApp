package com.example.kepoapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.kepoapp.R;
import com.example.kepoapp.databinding.ActivityMainMenuBinding;
import com.example.kepoapp.model.SharedPref;
import com.example.kepoapp.model.User;

public class MainMenuActivity extends AppCompatActivity {

    public final static String Extra_User = "extra_user";
    private ActivityMainMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_menu);

        User user = (User) getIntent().getSerializableExtra(Extra_User);
        binding.setUser(user);
        binding.introMessage.setText("Welcome " + user.getUsername());

        binding.MyToDoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, MyToDoActivity.class);
                startActivity(intent);
            }
        });

        binding.SearchToDoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, SearchToDoActivity.class);
                startActivity(intent);
            }
        });

        binding.SearchUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, SearchUserActivity.class);
                startActivity(intent);
            }
        });

        binding.ProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, ProfileActivity.class);
                intent.putExtra(Extra_User, user);
                startActivity(intent);
            }
        });

    }
}