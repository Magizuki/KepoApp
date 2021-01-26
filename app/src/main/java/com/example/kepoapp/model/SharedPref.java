package com.example.kepoapp.model;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {

    private SharedPreferences sharedPreferences;
    private  SharedPreferences.Editor editor;

    public  SharedPref(Context ctx){
        this.sharedPreferences = ctx.getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void save(User user){

        editor.putInt("id", user.getId());
        editor.putString("username", user.getUsername());
        editor.putString("password", user.getPassword());
        editor.apply();

    }

    public User load(){
        int id = sharedPreferences.getInt("id", 0);
        String username = sharedPreferences.getString("username", "");
        String password = sharedPreferences.getString("password", "");
        User user = new User(id, username, password);
        return user;
    }


    public void clearSharedPref(){
        editor.clear();
        editor.apply();
    }



}
