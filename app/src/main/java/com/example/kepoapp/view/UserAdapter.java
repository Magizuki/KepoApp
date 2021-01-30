package com.example.kepoapp.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kepoapp.databinding.ActivitySearchUserBinding;
import com.example.kepoapp.databinding.ActivitySearchUserRowsBinding;
import com.example.kepoapp.model.User;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private ArrayList<User> users;
    private Context ctx;

    public UserAdapter(Context ctx, ArrayList<User> users){
        this.ctx = ctx;
        this.users = users;
    }

    public void update(ArrayList<User> newUser){
        users.clear();
        users.addAll(newUser);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ActivitySearchUserRowsBinding databinding = ActivitySearchUserRowsBinding.inflate(inflater, parent, false);
        return new ViewHolder(databinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = users.get(position);
        holder.binding.setUser(user);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ActivitySearchUserRowsBinding binding;
        public ViewHolder(@NonNull ActivitySearchUserRowsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.Item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ctx, UserDetailActivity.class);
                    intent.putExtra("User", users.get(getAdapterPosition()));
                    ctx.startActivity(intent);
                }
            });

        }
    }


}
