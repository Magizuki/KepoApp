package com.example.kepoapp.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.kepoapp.model.User;
import com.example.kepoapp.view.LoginRegisterActivity;

public class DatabaseHelper extends SQLiteOpenHelper {

//    public DatabaseHelper getInstance(DatabaseHelper db, Context ctx){
//        if(db == null){
//            return new DatabaseHelper(ctx);
//        }
//        return db;
//    }

    public DatabaseHelper(@Nullable Context context) {
        super(context, "KepoApp.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create_UserTable_query = "CREATE TABLE Users ( id INTEGER PRIMARY KEY AUTOINCREMENT, Username TEXT , Password TEXT)";
        db.execSQL(create_UserTable_query);
        String create_ToDoListTable_query = "CREATE TABLE ToDoLists (id INTEGER PRIMARY KEY AUTOINCREMENT, UserID INTEGER, LastEditDate DATE, ToDoName TEXT, DESCRIPTION TEXT, FOREIGN KEY (UserID) REFERENCES Users(id))";
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


}
