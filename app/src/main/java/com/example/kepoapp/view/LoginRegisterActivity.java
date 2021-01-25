package com.example.kepoapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kepoapp.R;
import com.example.kepoapp.controller.AuthController;
import com.example.kepoapp.controller.DatabaseHelper;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class LoginRegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username, password;
    private Button login, register;
    AuthController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);

        username = findViewById(R.id.usernameTxt);
        password = findViewById(R.id.passwordTxt);
        login = findViewById(R.id.loginBtn);
        register = findViewById(R.id.registerBtn);

        login.setOnClickListener(this);
        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if(view.equals(register)){
            String Username = username.getText().toString();
            String Password = password.getText().toString();

            controller = new AuthController();

            //Field is empty
            if(controller.validateRegisterData(Username, Password, this) == 1){
                BottomSheet dialog = new BottomSheet(1);
                dialog.show(getSupportFragmentManager(), "TAG");
            }
            //Username already exist
            else if(controller.validateRegisterData(Username, Password, this) == 2){
                BottomSheet dialog = new BottomSheet(2);
                dialog.show(getSupportFragmentManager(), "TAG");
            }
            //Register success
            else{
                Toast.makeText(LoginRegisterActivity.this, "Register Success",Toast.LENGTH_LONG).show();
            }

        }
        else if(view.equals(login)){
            Toast.makeText(LoginRegisterActivity.this,"tes",Toast.LENGTH_LONG).show();
        }

    }
}