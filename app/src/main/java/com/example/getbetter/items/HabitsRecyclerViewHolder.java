package com.example.getbetter.items;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.getbetter.R;
import com.example.getbetter.activities.HabitActivity;
import com.example.getbetter.databinding.ItemHabitsBinding;
import com.example.getbetter.model.Habit;

public class HabitsRecyclerViewHolder extends RecyclerView.ViewHolder {

    private final TextView name;
    private final TextView type;
    private final TextView action;

    public HabitsRecyclerViewHolder(@NonNull ItemHabitsBinding binding) {
        super(binding.getRoot());
        name = binding.itemHabitName;
        type = binding.itemHabitType;
        action = binding.itemHabitAction;
    }

    public void createItem(Habit habits){
        if (habits==null)return;
        name.setText(habits.getName());

        if (habits.getType()){
            type.setTextColor(itemView.getResources().getColor(R.color.green));
            type.setText(itemView.getContext().getString(R.string.good_habit));

            action.setText(itemView.getContext().getString(R.string.acquisition));
        } else {
            type.setTextColor(itemView.getResources().getColor(R.color.red));
            type.setText(itemView.getContext().getString(R.string.bad_habit));

            action.setText(itemView.getContext().getString(R.string.quit_it));
        }

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( itemView.getContext() , HabitActivity.class);
                intent.putExtra("id" , habits.getId());
                intent.putExtra("name" , habits.getName());
                intent.putExtra("type" , habits.getType());
                itemView.getContext().startActivity( intent );
            }
        });
    }
}
