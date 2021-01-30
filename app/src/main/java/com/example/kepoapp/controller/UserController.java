package com.example.kepoapp.controller;

import android.content.Context;

import com.example.kepoapp.model.User;

import java.util.ArrayList;

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

    public ArrayList<User> getAllUserList(int Userid){
        return dbhelper.getAllUserList(Userid);
    }

    public ArrayList<User> getAllUser(String name){
        return dbhelper.getAllUser(name);
    }
}
