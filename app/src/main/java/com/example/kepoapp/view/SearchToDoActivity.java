package com.example.kepoapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.kepoapp.R;
import com.example.kepoapp.controller.ToDoController;
import com.example.kepoapp.controller.UserController;
import com.example.kepoapp.databinding.ActivitySearchToDoBinding;
import com.example.kepoapp.model.SharedPref;
import com.example.kepoapp.model.ToDoList;
import com.example.kepoapp.model.User;

import java.util.ArrayList;

public class SearchToDoActivity extends AppCompatActivity {

    private ActivitySearchToDoBinding binding;
    private SharedPref sharedPref;
    private UserToDoAdapter adapter;
    private ToDoController todoController;
    private UserController userController;
    private ArrayList<ToDoList> toDoLists;
    private  ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(SearchToDoActivity.this, R.layout.activity_search_to_do);
        sharedPref = new SharedPref(this);
        User user = sharedPref.load();
        userController = new UserController(this);
        todoController = new ToDoController(this);

        toDoLists = todoController.getAllToDoList(user.getId());
        users = new ArrayList<User>();
        for (int a = 0; a < toDoLists.size(); a++) {
            users.add(userController.getUser(toDoLists.get(a).getUserID()));
        }

        if(toDoLists.isEmpty()){
            binding.NoDataView.setVisibility(View.VISIBLE);
            binding.ToDoRV.setVisibility(View.INVISIBLE);
        }
        else if(!toDoLists.isEmpty()){
            binding.NoDataView.setVisibility(View.INVISIBLE);
            binding.ToDoRV.setVisibility(View.VISIBLE);

            adapter = new UserToDoAdapter(SearchToDoActivity.this, toDoLists, users);
            binding.ToDoRV.setLayoutManager(new LinearLayoutManager(this));
            binding.ToDoRV.setAdapter(adapter);
        }

        binding.BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchToDoActivity.this, MainMenuActivity.class);
                intent.putExtra(MainMenuActivity.Extra_User, user);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        binding.SearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.byToDoCheck.isChecked() && binding.byUserCheck.isChecked()){
                    searchByUserAndToDo();
                }
                else if(binding.byToDoCheck.isChecked()){
                    searchByToDo();
                }
                else if(binding.byUserCheck.isChecked()){
                    searchByUser();
                }
                else{
                    Toast.makeText(SearchToDoActivity.this, "Please Check Minimum one of the checkbox below Edit Text field", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void searchByUserAndToDo(){
        String name = binding.ToDoTxt.getText().toString();
        binding.ResultForView.setText("Result for '" + name + "'");
        User searchUser = userController.getUserByName(name);
        ArrayList<ToDoList> newToDoLists = todoController.getAllToDoListByNameAndUserID( name, searchUser.getId());
        ArrayList<User> newUsers = new ArrayList<User>();
        for (ToDoList todo: newToDoLists) {
            newUsers.add(userController.getUser(todo.getUserID()));
        }

        if(newToDoLists.isEmpty()){
            binding.NoDataView.setVisibility(View.VISIBLE);
            binding.ToDoRV.setVisibility(View.INVISIBLE);
        }
        else if(!newToDoLists.isEmpty()) {
            binding.NoDataView.setVisibility(View.INVISIBLE);
            binding.ToDoRV.setVisibility(View.VISIBLE);
        }
        adapter.update(newToDoLists, newUsers);

    }

    private void searchByUser(){
        String name = binding.ToDoTxt.getText().toString();
        binding.ResultForView.setText("Result for '" + name + "'");
        User searchUser = userController.getUserByName(name);
        ArrayList<ToDoList> newToDoLists = todoController.getAllMyToDoList(searchUser.getId());
        ArrayList<User> newUsers = new ArrayList<User>();
        for (ToDoList todo: newToDoLists) {
            newUsers.add(userController.getUser(todo.getUserID()));
        }

        if(newToDoLists.isEmpty()){
            binding.NoDataView.setVisibility(View.VISIBLE);
            binding.ToDoRV.setVisibility(View.INVISIBLE);
        }
        else if(!newToDoLists.isEmpty()) {
            binding.NoDataView.setVisibility(View.INVISIBLE);
            binding.ToDoRV.setVisibility(View.VISIBLE);
        }
        adapter.update(newToDoLists, newUsers);
    }

    private void searchByToDo(){
        String ToDoName = binding.ToDoTxt.getText().toString();
        binding.ResultForView.setText("Result for '" + ToDoName + "'");
        ArrayList<ToDoList> newToDoList = todoController.getAllToDoListByName(ToDoName);
        ArrayList<User> newUsers = new ArrayList<User>();
        for (ToDoList todo: newToDoList) {
            newUsers.add(userController.getUser(todo.getUserID()));
        }

        if(newToDoList.isEmpty()){
            binding.NoDataView.setVisibility(View.VISIBLE);
            binding.ToDoRV.setVisibility(View.INVISIBLE);
        }
        else if(!newToDoList.isEmpty()) {
            binding.NoDataView.setVisibility(View.INVISIBLE);
            binding.ToDoRV.setVisibility(View.VISIBLE);
        }
        adapter.update(newToDoList, newUsers);


    }
}