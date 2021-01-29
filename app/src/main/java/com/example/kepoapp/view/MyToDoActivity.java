package com.example.kepoapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.kepoapp.R;
import com.example.kepoapp.controller.ToDoController;
import com.example.kepoapp.databinding.ActivityMyToDoBinding;
import com.example.kepoapp.model.SharedPref;
import com.example.kepoapp.model.ToDoList;
import com.example.kepoapp.model.User;

import java.util.ArrayList;

public class MyToDoActivity extends AppCompatActivity {

    private ActivityMyToDoBinding binding;
    private SharedPref sharedPref;
    private ToDoController controller;
    private ToDoListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(MyToDoActivity.this, R.layout.activity_my_to_do);
        sharedPref = new SharedPref(this);

        User user =  sharedPref.load();
        controller = new ToDoController(MyToDoActivity.this);

        if(controller.checkMyToDo(user.getId())){
            binding.NoDataView.setVisibility(View.INVISIBLE);
            binding.MyToDoRV.setVisibility(View.VISIBLE);

            ArrayList<ToDoList> toDoLists = controller.getAllMyToDoList(user.getId());
            adapter = new ToDoListAdapter(this, toDoLists);
            binding.MyToDoRV.setLayoutManager(new LinearLayoutManager(this));
            binding.MyToDoRV.setAdapter(adapter);

        }
        else{
            binding.NoDataView.setVisibility(View.VISIBLE);
            binding.MyToDoRV.setVisibility(View.INVISIBLE);
        }

        binding.BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyToDoActivity.this, MainMenuActivity.class);
                intent.putExtra(MainMenuActivity.Extra_User, user);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        binding.AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyToDoActivity.this, AddUpdateMyToDoActivity.class);
                intent.putExtra(MainMenuActivity.Extra_User, user);
                intent.putExtra("CreateUpdateTitle", "Create ToDo");
                startActivity(intent);
            }
        });


    }
}