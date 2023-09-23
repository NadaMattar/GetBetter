package com.example.getbetter.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.getbetter.databinding.ItemUserHabitBinding;
import com.example.getbetter.items.UserHabitRecyclerViewHolder;
import com.example.getbetter.model.UserHabit;

import java.util.ArrayList;

public class UsersHabitsRecyclerAdapter extends RecyclerView.Adapter<UserHabitRecyclerViewHolder> {

    private ArrayList<UserHabit> userHabits = new ArrayList<>();

    public UsersHabitsRecyclerAdapter(ArrayList<UserHabit> userHabits) {
        this.userHabits = userHabits;
    }

    @NonNull
    @Override
    public UserHabitRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemUserHabitBinding binding = ItemUserHabitBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new UserHabitRecyclerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHabitRecyclerViewHolder holder, int position) {
        holder.createItem(userHabits.get(position));
    }

    @Override
    public int getItemCount() {
        if (userHabits!=null){
            return userHabits.size();
        }
        return 0 ;
    }}
