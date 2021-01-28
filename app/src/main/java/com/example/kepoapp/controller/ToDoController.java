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







}
