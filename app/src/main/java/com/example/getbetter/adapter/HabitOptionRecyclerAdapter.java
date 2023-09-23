package com.example.getbetter.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.getbetter.databinding.ItemHabitOptionBinding;
import com.example.getbetter.items.HabitOptionRecyclerViewHolder;

import com.example.getbetter.model.HabitOption;

import java.util.ArrayList;

public class HabitOptionRecyclerAdapter extends RecyclerView.Adapter<HabitOptionRecyclerViewHolder> {

    private ArrayList<HabitOption> habitOptions = new ArrayList<>();

    public HabitOptionRecyclerAdapter(ArrayList<HabitOption> habitOptions) {
        this.habitOptions = habitOptions;
    }

    @NonNull
    @Override
    public HabitOptionRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHabitOptionBinding binding = ItemHabitOptionBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new HabitOptionRecyclerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitOptionRecyclerViewHolder holder, int position) {
        holder.createItem(habitOptions.get(position));
    }

    @Override
    public int getItemCount() {
        if (habitOptions!=null){
            return habitOptions.size();
        }
        return 0 ;
    }
}
