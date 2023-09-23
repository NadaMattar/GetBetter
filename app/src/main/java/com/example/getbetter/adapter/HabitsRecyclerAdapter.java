package com.example.getbetter.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.getbetter.databinding.ItemHabitsBinding;
import com.example.getbetter.items.HabitsRecyclerViewHolder;
import com.example.getbetter.model.Habit;

import java.util.ArrayList;

public class HabitsRecyclerAdapter extends RecyclerView.Adapter<HabitsRecyclerViewHolder> {

    private ArrayList<Habit> habits = new ArrayList<>();

    public HabitsRecyclerAdapter(ArrayList<Habit> habits) {
        this.habits = habits;
    }

    @NonNull
    @Override
    public HabitsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHabitsBinding binding = ItemHabitsBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new HabitsRecyclerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitsRecyclerViewHolder holder, int position) {
        holder.createItem(habits.get(position));
    }

    @Override
    public int getItemCount() {
        if (habits!=null){
            return habits.size();
        }
        return 0 ;
    }
}
