package com.example.getbetter.items;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.getbetter.activities.HabitOptionsActivity;
import com.example.getbetter.databinding.ItemSectionBinding;
import com.example.getbetter.model.Section;

public class SectionRecyclerViewHolder extends RecyclerView.ViewHolder {

    private final TextView name;
    private final TextView bio;

    public SectionRecyclerViewHolder(@NonNull ItemSectionBinding binding) {
        super(binding.getRoot());
        name = binding.itemSectionTitle;
        bio = binding.itemSectionBio;
    }

    public void createItem(Section section){
        name.setText(section.getName());
        bio.setText(section.getBio());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( itemView.getContext() , HabitOptionsActivity.class);
                intent.putExtra("id" , section.getId());
                intent.putExtra("name" , section.getName());
                intent.putExtra("bio" , section.getBio());
                itemView.getContext().startActivity( intent );
            }
        });
    }
}
