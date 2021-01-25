package com.example.kepoapp.controller;

import android.content.Context;

import com.example.kepoapp.view.LoginRegisterActivity;

public class AuthController {

    DatabaseHelper db;
    public AuthController getInstance(AuthController controller){

        if(controller == null){
            return new AuthController();
        }

        return controller;
    }

    public int validateRegisterData(String username, String password, Context ctx){

        if(username.equals("") || password.equals("")){
            return 1;
        }
        else{

            db = new DatabaseHelper(ctx);

            if(!db.findUserbyUsername(username)){
                return 2;
            }

        }

        db.insertUsersTable(username, password);
        return 3;

    }



}
