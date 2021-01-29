package com.example.kepoapp.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.kepoapp.controller.ToDoController;
import com.example.kepoapp.model.ToDoList;

import java.util.ArrayList;

public class DeleteDialog extends DialogFragment {

    private ArrayList<ToDoList> toDoLists;
    private ToDoController controller;
    private Context ctx;

    public DeleteDialog(Context ctx, ArrayList<ToDoList> toDoLists){
        this.toDoLists = toDoLists;
        controller = new ToDoController(ctx);
        ctx = this.ctx;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext()).setTitle("Delete ToDo")
                .setMessage("Are you sure you want to delete all this todos ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        controller.deleteMyToDo(toDoLists);
                        Toast.makeText(ctx,"Delete Success", Toast.LENGTH_LONG).show();
                        dialogInterface.cancel();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.cancel();
                    }
                });

        return builder.create();
    }
}
