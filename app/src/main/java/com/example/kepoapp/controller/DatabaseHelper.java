package com.example.kepoapp.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.kepoapp.model.ToDoList;
import com.example.kepoapp.model.User;
import com.example.kepoapp.view.LoginRegisterActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

public class DatabaseHelper extends SQLiteOpenHelper {

//    public DatabaseHelper getInstance(DatabaseHelper db, Context ctx){
//        if(db == null){
//            return new DatabaseHelper(ctx);
//        }
//        return db;
//    }

    public DatabaseHelper(@Nullable Context context) {
        super(context, "KepoApps.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create_UserTable_query = "CREATE TABLE Users ( id INTEGER PRIMARY KEY AUTOINCREMENT, Username TEXT , Password TEXT)";
        db.execSQL(create_UserTable_query);
        String create_ToDoListTable_query = "CREATE TABLE ToDoLists (id INTEGER PRIMARY KEY AUTOINCREMENT, UserID INTEGER, LastEditDate TEXT, ToDoName TEXT, Description TEXT, FOREIGN KEY (UserID) REFERENCES Users(id))";
        db.execSQL(create_ToDoListTable_query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS users");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ToDoLists");
    }

    public void insertUsersTable(String username, String password){

        String insert_UserTable_query = "INSERT INTO Users(Username, Password) VALUES ( '"+username+"', '" +password+ "'); ";
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(insert_UserTable_query);

        db.close();

    }

    public boolean findUserbyUsername(String username){

        String query = "SELECT * FROM Users where Username = " + "'" + username + "'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor;

        cursor = db.rawQuery(query, null);

        if(cursor.getCount() > 1){
            cursor.close();
            db.close();
            return false;
        }

        cursor.close();
        db.close();
        return true;
    }

    public boolean findMyToDobyUserId(int id){

        String query = "SELECT * FROM ToDoLists where UserID = " + "'" + id + "'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor;

        cursor = db.rawQuery(query, null);

        if(cursor.getCount() > 1){
            cursor.close();
            db.close();
            return true;
        }

        cursor.close();
        db.close();
        return false;


    }

    public ArrayList<ToDoList> getAllMyToDoList(int id){

        ArrayList<ToDoList> toDoLists = new ArrayList<>();
        String query = "SELECT * FROM ToDoLists where UserID = " + "'" + id + "'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor;

        cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){

            do{
                int ToDoID = cursor.getInt(0);
                int UserID = cursor.getInt(1);
                String lastupdate = cursor.getString(2);
                String name = cursor.getString(3);
                String desc = cursor.getString(4);
                toDoLists.add(new ToDoList(ToDoID, UserID, lastupdate, name, desc));
            }while (cursor.moveToNext());

        }

        return toDoLists;
    }

    public User login(String username, String password){

        String query = "SELECT * FROM Users where Username = " + "'" + username + "' AND Password = '" + password + "' ;";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        User user;
        if(cursor.getCount() > 1){


            if(cursor.moveToFirst()){
                int id = cursor.getInt(0);
                String Name = cursor.getString(1);
                String Password = cursor.getString(2);

                user = new User(id, Name, Password);
                cursor.close();
                db.close();

                return user;
            }

        }

        user = new User(0, "fail", "fail");
        cursor.close();
        db.close();

        return user;
    }

    public void createMyToDo(int userID, String title, String description){

        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm");
        String date = dateFormat.format(calendar.getTime());

        String insert_ToDoListTable_query = "INSERT INTO ToDoLists(UserID, LastEditDate, ToDoName, Description) VALUES ('"+userID+"', '" +date+ "', '" +title+ "', '" +description+ "'); ";
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(insert_ToDoListTable_query);

        db.close();

    }

    public void updateMyToDo(int id, String title, String description){

        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm");
        String date = dateFormat.format(calendar.getTime());

        String update_ToDoListTable_query = "UPDATE ToDoLists SET LastEditDate = '" + date + "' , ToDoName = '" + title + "' , Description = '" + description + "' WHERE id = '" + id + "' ;";
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(update_ToDoListTable_query);

        db.close();

    }

    public void deleteMyToDo(ArrayList<ToDoList> toDoLists){

        SQLiteDatabase db = this.getReadableDatabase();
        for (ToDoList todo: toDoLists) {
            String delete_ToDoListTable_query = "DELETE FROM ToDoLists WHERE id = '" + todo.getId() + "' ;";
            db.execSQL(delete_ToDoListTable_query);
        }
        db.close();
    }

    public ArrayList<ToDoList> getAllToDoList(int id){

        ArrayList<ToDoList> toDoLists = new ArrayList<>();
        String query = "SELECT * FROM ToDoLists where UserID != " + "'" + id + "' ;";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor;

        cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){

            do{
                int ToDoID = cursor.getInt(0);
                int UserID = cursor.getInt(1);
                String lastupdate = cursor.getString(2);
                String name = cursor.getString(3);
                String desc = cursor.getString(4);
                toDoLists.add(new ToDoList(ToDoID, UserID, lastupdate, name, desc));
            }while (cursor.moveToNext());

        }

        return toDoLists;
    }

    public User getUser(int UserID){

        String query = "SELECT * FROM Users where id = '" + UserID + "' ;";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);


        if(cursor.moveToFirst()){
            int id = cursor.getInt(0);
            String Name = cursor.getString(1);
            String Password = cursor.getString(2);


            cursor.close();
            db.close();

            return new User(id, Name, Password);
        }



        cursor.close();
        db.close();

        return null;

    }

    public User getUserByName(String name){

        String query = "SELECT * FROM Users where Username = '" + name + "' ;";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        User user;

        if(cursor.moveToFirst()){
            int id = cursor.getInt(0);
            String Name = cursor.getString(1);
            String Password = cursor.getString(2);

            user = new User(id, Name, Password);
            cursor.close();
            db.close();

            return user;
        }



        user = new User(0, "fail", "fail");
        cursor.close();
        db.close();

        return user;

    }

    public ArrayList<ToDoList> getAllToDoListByName(String ToDoname){
        ArrayList<ToDoList> toDoLists = new ArrayList<>();
        String query = "SELECT * FROM ToDoLists where ToDoName = " + "'" + ToDoname + "' ;";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor;

        cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){

            do{
                int ToDoID = cursor.getInt(0);
                int UserID = cursor.getInt(1);
                String lastupdate = cursor.getString(2);
                String name = cursor.getString(3);
                String desc = cursor.getString(4);
                toDoLists.add(new ToDoList(ToDoID, UserID, lastupdate, name, desc));
            }while (cursor.moveToNext());

        }

        return toDoLists;
    }

    public ArrayList<ToDoList> getAllToDoListByNameAndUserID(String ToDoname, int UserId){
        ArrayList<ToDoList> toDoLists = new ArrayList<>();
        String query = "SELECT * FROM ToDoLists where ToDoName = " + "'" + ToDoname + "' OR UserID = '" + UserId + "' ;";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor;

        cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){

            do{
                int ToDoID = cursor.getInt(0);
                int UserID = cursor.getInt(1);
                String lastupdate = cursor.getString(2);
                String name = cursor.getString(3);
                String desc = cursor.getString(4);
                toDoLists.add(new ToDoList(ToDoID, UserID, lastupdate, name, desc));
            }while (cursor.moveToNext());

        }

        return toDoLists;
    }


}
