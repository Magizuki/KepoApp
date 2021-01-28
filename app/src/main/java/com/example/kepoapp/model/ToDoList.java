package com.example.kepoapp.model;

import java.io.Serializable;

public class ToDoList implements Serializable {

    private int id, UserID;
    private String date, name, description;

    public ToDoList(int id, int userID, String date, String name, String description) {
        this.id = id;
        UserID = userID;
        this.date = date;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
