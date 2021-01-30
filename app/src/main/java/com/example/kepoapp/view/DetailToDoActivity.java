package com.example.kepoapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.kepoapp.R;
import com.example.kepoapp.databinding.ActivityDetailToDoBinding;
import com.example.kepoapp.model.SharedPref;
import com.example.kepoapp.model.ToDoList;
import com.example.kepoapp.model.User;

public class DetailToDoActivity extends AppCompatActivity {

    private ActivityDetailToDoBinding binding;
    private SharedPref sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPref = new SharedPref(DetailToDoActivity.this);
        User user = sharedPref.load();
        binding = DataBindingUtil.setContentView(DetailToDoActivity.this, R.layout.activity_detail_to_do);
        ToDoList toDoDetail = (ToDoList) getIntent().getSerializableExtra("ToDoDetail");
        binding.setTodo(toDoDetail);

        if(toDoDetail.getUserID() != user.getId()){
            binding.UpdateBtn.setVisibility(View.INVISIBLE);
        }

        binding.UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailToDoActivity.this, AddUpdateMyToDoActivity.class);
                intent.putExtra(MainMenuActivity.Extra_User, user);
                intent.putExtra("ToDoDetail", toDoDetail);
                intent.putExtra("CreateUpdateTitle", "Update ToDo");
                startActivity(intent);
            }
        });

        binding.BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(toDoDetail.getUserID() != user.getId()){
                    Intent intent = new Intent(DetailToDoActivity.this, SearchToDoActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
                else{
                    Intent intent = new Intent(DetailToDoActivity.this, MyToDoActivity.class);
                    intent.putExtra(MainMenuActivity.Extra_User, user);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();

                }


            }
        });

    }
}