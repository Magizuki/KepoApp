package com.example.kepoapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;

import com.example.kepoapp.R;
import com.example.kepoapp.controller.ToDoController;
import com.example.kepoapp.controller.UserController;
import com.example.kepoapp.databinding.ActivityUserDetailBinding;
import com.example.kepoapp.model.SharedPref;
import com.example.kepoapp.model.ToDoList;
import com.example.kepoapp.model.User;

import java.util.ArrayList;

public class UserDetailActivity extends AppCompatActivity {

    private ActivityUserDetailBinding binding;
    private SharedPref sharedPref;
    private ArrayList<ToDoList> toDoLists;
    private ToDoController ToDocontroller;
    private UserController Usercontroller;
    private UserToDoAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(UserDetailActivity.this, R.layout.activity_user_detail);
        sharedPref = new SharedPref(this);
        User user = sharedPref.load();
        User selected_user = (User) getIntent().getSerializableExtra("User");
        binding.setUser(selected_user);
        ToDocontroller = new ToDoController(this);
        Usercontroller = new UserController(this);
        toDoLists = ToDocontroller.getAllMyToDoList(selected_user.getId());
        ArrayList<User> users = new ArrayList<User>();
        for (int a = 0; a < toDoLists.size(); a++) {
            users.add(Usercontroller.getUser(toDoLists.get(a).getUserID()));
        }
        
        binding.countView.setText("Todos : " + toDoLists.size());
        
        adapter = new UserToDoAdapter(this, toDoLists, users);
        binding.ToDoRV.setLayoutManager(new LinearLayoutManager(this));
        binding.ToDoRV.setAdapter(adapter);

        binding.BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserDetailActivity.this, SearchUserActivity.class);
                startActivity(intent);
            }
        });

    }
}