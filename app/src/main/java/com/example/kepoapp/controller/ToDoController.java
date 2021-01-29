package com.example.kepoapp.controller;

import android.content.Context;

import com.example.kepoapp.model.ToDoList;
import com.example.kepoapp.model.User;

import java.util.ArrayList;

public class ToDoController {

    private DatabaseHelper dbhelper;

    public ToDoController(Context ctx){
        dbhelper = new DatabaseHelper(ctx);
    }

    public boolean checkMyToDo(int idUser){

        if(dbhelper.findMyToDobyUserId(idUser)){
            return true;
        }
        return false;
    }

    public ArrayList<ToDoList> getAllMyToDoList(int idUser){

        return dbhelper.getAllMyToDoList(idUser);

    }

    public int checkData(String title, String description){

        if(title.equals("") || description.equals("")){
            return 1;
        }
        else if(description.length() > 100){
            return 2;
        }
        return 0;
    }

    public void createMyToDo(int userID, String title, String description){
        dbhelper.createMyToDo(userID, title, description);
    }

    public void updateMyToDo(int id, String title, String description){
        dbhelper.updateMyToDo(id, title, description);
    }



}
