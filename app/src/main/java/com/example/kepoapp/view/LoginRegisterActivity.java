package com.example.kepoapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kepoapp.R;
import com.example.kepoapp.controller.AuthController;
import com.example.kepoapp.controller.DatabaseHelper;
import com.example.kepoapp.model.SharedPref;
import com.example.kepoapp.model.User;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class LoginRegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username, password;
    private Button login, register;
    private SharedPref sharedPref;
    AuthController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        sharedPref = new SharedPref(LoginRegisterActivity.this);


        username = findViewById(R.id.usernameTxt);
        password = findViewById(R.id.passwordTxt);
        login = findViewById(R.id.loginBtn);
        register = findViewById(R.id.registerBtn);

        login.setOnClickListener(this);
        register.setOnClickListener(this);

        User user = sharedPref.load();
        if(user.getId() != 0 || user.getUsername() != "" || user.getPassword() != ""){
//            sharedPref.clearSharedPref();
            Intent intent = new Intent(LoginRegisterActivity.this, MainMenuActivity.class);
            intent.putExtra(MainMenuActivity.Extra_User, user);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onClick(View view) {

        String Username = username.getText().toString();
        String Password = password.getText().toString();

        if(view.equals(register)){

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

            User user;
            controller = new AuthController();
            //If Field is empty
            if(username.getText().toString().equals("") || password.getText().toString().equals("")){
                BottomSheet dialog = new BottomSheet(1);
                dialog.show(getSupportFragmentManager(), "TAG");
                return;
            }
            user = controller.validateLoginData(Username, Password, this);
            //If Login Data not valid
            if(user.getId() == 0 && user.getUsername().equals("fail") && user.getPassword().equals("fail")){
                BottomSheet dialog = new BottomSheet(3);
                dialog.show(getSupportFragmentManager(), "TAG");
            }
            else{
                sharedPref.save(user);
                Intent intent = new Intent(LoginRegisterActivity.this, MainMenuActivity.class);
                intent.putExtra(MainMenuActivity.Extra_User, user);
                startActivity(intent);
                finish();
            }

        }

    }
}