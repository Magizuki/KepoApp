package com.example.kepoapp.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kepoapp.controller.ToDoController;
import com.example.kepoapp.databinding.ActivityMyToDoRowsBinding;
import com.example.kepoapp.model.ToDoList;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ViewHolder> {

    private ArrayList<ToDoList> toDoLists;
    private ArrayList<ToDoList> selectedToDoList = new ArrayList<ToDoList>();
    private Context ctx;
    int count = 0;
    private ToDoController controller;
    Snackbar snackbar = null;

    public ToDoListAdapter(Context ctx, ArrayList<ToDoList> toDoLists){
        this.ctx = ctx;
        this.toDoLists = toDoLists;
        controller = new ToDoController(ctx);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ActivityMyToDoRowsBinding databinding = ActivityMyToDoRowsBinding.inflate(inflater, parent, false);
        return new ViewHolder(databinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ToDoList toDoList = toDoLists.get(position);
        holder.binding.setTodo(toDoList);
        holder.binding.MyToDoCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(holder.binding.MyToDoCheck.isChecked()){
                    count++;
                    selectedToDoList.add(toDoLists.get(holder.getAdapterPosition()));
                    snackbar = Snackbar.make(view, count+" Item(s)", Snackbar.LENGTH_INDEFINITE);
                    snackbar.setAction("Delete", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                            DeleteDialog dialog = new DeleteDialog(ctx, selectedToDoList);
                            AlertDialog dialog = new AlertDialog.Builder(ctx).setTitle("Delete ToDo")
                                    .setMessage("Are you sure you want to delete all this todos ?")
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            controller.deleteMyToDo(selectedToDoList);
                                            Toast.makeText(ctx,"Delete Success", Toast.LENGTH_LONG).show();
                                            notifyDataSetChanged();
                                            snackbar.dismiss();
                                            dialogInterface.cancel();
                                        }
                                    })
                                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                        }
                                    }).create();
                            dialog.show();
                        }
                    });
                    snackbar.show();
                }
                else{
                    count--;
                    for(int a = 0; a < selectedToDoList.size(); a++){
                        if(selectedToDoList.get(a).getId() == toDoLists.get(holder.getAdapterPosition()).getId()){
                            selectedToDoList.remove(a);
                            break;
                        }
                    }
                    if(count == 0){
                        snackbar.dismiss();
                        return;
                    }
                    snackbar.setText(count+" Item(s)");

                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return toDoLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ActivityMyToDoRowsBinding binding;


        public ViewHolder(@NonNull ActivityMyToDoRowsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


            binding.Item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToDoList ToDo = toDoLists.get(getAdapterPosition());
                    Intent intent = new Intent(ctx, DetailToDoActivity.class);
                    intent.putExtra("ToDoDetail", ToDo);
                    ctx.startActivity(intent);
                }
            });
//
//            binding.MyToDoCheck.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if(binding.MyToDoCheck.isChecked()){
//                        selectedToDoList.add(toDoLists.get(getAdapterPosition()));
//                    }
//                    else{
//                        for(int a = 0; a < selectedToDoList.size(); a++){
//                            if(selectedToDoList.get(a).getId() == toDoLists.get(getAdapterPosition()).getId()){
//                                selectedToDoList.remove(a);
//                                break;
//                            }
//                        }
//                    }
//                }
//            });

        }
    }


}
