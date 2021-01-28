package com.example.kepoapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kepoapp.databinding.ActivityMyToDoRowsBinding;
import com.example.kepoapp.model.ToDoList;

import java.util.ArrayList;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ViewHolder> {

    private ArrayList<ToDoList> toDoLists;
    private Context ctx;

    public ToDoListAdapter(Context ctx, ArrayList<ToDoList> toDoLists){
        this.ctx = ctx;
        this.toDoLists = toDoLists;
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
        }
    }


}
