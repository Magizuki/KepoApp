package com.example.kepoapp.controller;

import android.content.Context;

import com.example.kepoapp.model.User;

public class UserController {

    private DatabaseHelper dbhelper;

    public UserController(Context ctx){
        dbhelper = new DatabaseHelper(ctx);
    }

    public User getUser(int userID){
        return dbhelper.getUser(userID);
    }

    public User getUserByName(String name){
        return dbhelper.getUserByName(name);
    }
}
