package com.example.kepoapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.kepoapp.R;
import com.example.kepoapp.controller.UserController;
import com.example.kepoapp.databinding.ActivitySearchUserBinding;
import com.example.kepoapp.model.SharedPref;
import com.example.kepoapp.model.User;

import java.util.ArrayList;

public class SearchUserActivity extends AppCompatActivity {

    private ActivitySearchUserBinding binding;
    private SharedPref sharedPref;
    private ArrayList<User> users;
    private UserController controller;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(SearchUserActivity.this, R.layout.activity_search_user);
        sharedPref = new SharedPref(this);
        User user = sharedPref.load();
        controller = new UserController(this);
        users = controller.getAllUserList(user.getId());
        adapter = new UserAdapter(this, users);

        binding.UserRV.setLayoutManager(new LinearLayoutManager(this));
        binding.UserRV.setAdapter(adapter);

        binding.BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchUserActivity.this, MainMenuActivity.class);
                intent.putExtra(MainMenuActivity.Extra_User, user);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        binding.SearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.UserTxt.getText().toString();
                User newUser = controller.getUserByName(name);
                ArrayList<User> newUsers = new ArrayList<User>();
                binding.ResultForView.setVisibility(View.VISIBLE);
                binding.ResultForView.setText("Result '" + name + "'");
                newUsers.add(newUser);
                adapter.update(newUsers);
            }
        });

    }
}