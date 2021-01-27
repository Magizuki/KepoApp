package com.example.kepoapp.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.kepoapp.R;
import com.example.kepoapp.databinding.ActivityProfileBinding;
import com.example.kepoapp.model.SharedPref;
import com.example.kepoapp.model.User;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;
    private SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(ProfileActivity.this, R.layout.activity_profile);
        User user = (User) getIntent().getSerializableExtra(MainMenuActivity.Extra_User);
        sharedPref = new SharedPref(ProfileActivity.this);

        binding.setUser(user);

        binding.NameView.setText(user.getUsername());
        binding.UserameView.setText(user.getUsername());

        binding.BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, MainMenuActivity.class);
                intent.putExtra(MainMenuActivity.Extra_User, user);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        binding.LogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogoutDialog dialog = new LogoutDialog();
                dialog.show(getSupportFragmentManager(), "");
            }
        });

    }


}