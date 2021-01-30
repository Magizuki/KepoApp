package com.example.kepoapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kepoapp.databinding.ActivitySearchToDoRowsBinding;
import com.example.kepoapp.model.ToDoList;
import com.example.kepoapp.model.User;

import java.util.ArrayList;

public class UserToDoAdapter extends RecyclerView.Adapter<UserToDoAdapter.ViewHolder> {

    private ArrayList<ToDoList> toDoLists;
    private ArrayList<User> users;
    private Context ctx;

    public UserToDoAdapter(Context ctx, ArrayList<ToDoList> toDoLists, ArrayList<User> users){
        this.ctx = ctx;
        this.toDoLists = toDoLists;
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ActivitySearchToDoRowsBinding databinding = ActivitySearchToDoRowsBinding.inflate(inflater, parent, false);
        return new ViewHolder(databinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ToDoList toDoList = toDoLists.get(position);
        User user = users.get(position);
        holder.binding.setTodo(toDoList);
        holder.binding.setUser(user);
    }

    public void update(ArrayList<ToDoList> newToDoLists, ArrayList<User> newUsers){
        users.clear();
        toDoLists.clear();
        toDoLists.addAll(newToDoLists);
        users.addAll(newUsers);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return toDoLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ActivitySearchToDoRowsBinding binding;

        public ViewHolder(@NonNull ActivitySearchToDoRowsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
