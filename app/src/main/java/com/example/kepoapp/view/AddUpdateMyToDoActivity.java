package com.example.kepoapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.kepoapp.R;
import com.example.kepoapp.controller.ToDoController;
import com.example.kepoapp.databinding.ActivityAddUpdateMyToDoBinding;
import com.example.kepoapp.model.ToDoList;
import com.example.kepoapp.model.User;

public class AddUpdateMyToDoActivity extends AppCompatActivity {

    private ActivityAddUpdateMyToDoBinding binding;
    private User user;
    private ToDoList toDoList;
    private ToDoController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(AddUpdateMyToDoActivity.this, R.layout.activity_add_update_my_to_do);

        String title = getIntent().getStringExtra("CreateUpdateTitle");
        user = (User) getIntent().getSerializableExtra(MainMenuActivity.Extra_User);
        toDoList = (ToDoList) getIntent().getSerializableExtra("ToDoDetail");

        binding.CreateUpdateToDoTitle.setText(title);
        binding.LengthView.setText("0/0");
        controller = new ToDoController(AddUpdateMyToDoActivity.this);

        binding.CheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int flag = controller.checkData(binding.TitleTxt.getText().toString(), binding.DescriptionTxt.getText().toString());

                if(flag == 1){
                    binding.ErrorView.setText("Text field Cannot be empty");
                }
                else if(flag == 2){
                    Toast.makeText(AddUpdateMyToDoActivity.this, "Your description exceeded the maximum words", Toast.LENGTH_LONG).show();
                }
                else if(flag == 0){
                    int userID = user.getId();
                    String title = binding.TitleTxt.getText().toString();
                    String description = binding.DescriptionTxt.getText().toString();
                    if(binding.CreateUpdateToDoTitle.getText().toString().equals("Create ToDo")){
                        controller.createMyToDo(userID, title, description);
                        Intent intent = new Intent(AddUpdateMyToDoActivity.this, MyToDoActivity.class);
                        startActivity(intent);
                    }
                    else if(binding.CreateUpdateToDoTitle.getText().toString().equals("Update ToDo")){
                        controller.updateMyToDo(toDoList.getId(), title, description);
                        Intent intent = new Intent(AddUpdateMyToDoActivity.this, MyToDoActivity.class);
                        intent.putExtra(MainMenuActivity.Extra_User, user);
                        startActivity(intent);
                    }

                }

            }
        });

        binding.DescriptionTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String text = charSequence.toString();
                int length = text.length();
                binding.LengthView.setText(length + "/100");

                if(length > 100){
                    binding.ErrorView.setText("Your description exceeded the maximum words");
                    binding.LengthView.setTextColor(getResources().getColor(R.color.design_default_color_error));
                }
                else{
                    binding.ErrorView.setText("");
                    binding.LengthView.setTextColor(getResources().getColor(R.color.light_gray));
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

        binding.BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if(binding.CreateUpdateToDoTitle.getText().toString().equals("Create ToDo")){
                    intent = new Intent(AddUpdateMyToDoActivity.this, MyToDoActivity.class);
                    intent.putExtra(MainMenuActivity.Extra_User, user);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
                else if(binding.CreateUpdateToDoTitle.getText().toString().equals("Update ToDo")){
                    intent = new Intent(AddUpdateMyToDoActivity.this, DetailToDoActivity.class);
                    intent.putExtra(MainMenuActivity.Extra_User, user);
                    intent.putExtra("ToDoDetail", toDoList);
                    startActivity(intent);
                    finish();
                }

            }
        });

    }
}