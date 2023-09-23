package com.example.getbetter.items;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.getbetter.R;
import com.example.getbetter.databinding.ItemHabitOptionBinding;
import com.example.getbetter.model.HabitOption;

import java.util.function.ObjIntConsumer;

public class HabitOptionRecyclerViewHolder extends RecyclerView.ViewHolder {

    TextView text;
    public HabitOptionRecyclerViewHolder(@NonNull ItemHabitOptionBinding binding) {
        super(binding.getRoot());
        text = binding.itemHabitOptionText;

    }

    public void createItem(HabitOption habitOption){
        text.setText( habitOption.getText());
    }
}
