package com.example.getbetter.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.getbetter.databinding.ItemSectionBinding;
import com.example.getbetter.items.SectionRecyclerViewHolder;
import com.example.getbetter.model.Section;

import java.util.ArrayList;

public class SectionsRecyclerAdapter extends RecyclerView.Adapter<SectionRecyclerViewHolder> {

    private ArrayList<Section> sections = new ArrayList<>();

    public SectionsRecyclerAdapter(ArrayList<Section> sections) {
        this.sections = sections;
    }

    @NonNull
    @Override
    public SectionRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSectionBinding binding = ItemSectionBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new SectionRecyclerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SectionRecyclerViewHolder holder, int position) {
        holder.createItem(sections.get(position));
    }

    @Override
    public int getItemCount() {
        if (sections!=null){
            return sections.size();
        }
        return 0 ;
    }}
