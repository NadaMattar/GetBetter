package com.example.getbetter.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.getbetter.databinding.ItemOnBoardingBinding;
import com.example.getbetter.items.OnBoardingSliderViewHolder;
import com.example.getbetter.model.OnBoarding;

import java.util.ArrayList;

public class OnBoardingSliderPagerAdapter extends RecyclerView.Adapter<OnBoardingSliderViewHolder> {

    private ArrayList<OnBoarding> onBoardings = new ArrayList<>();

    public OnBoardingSliderPagerAdapter(ArrayList<OnBoarding> onBoardings) {
        this.onBoardings = onBoardings;
    }

    @NonNull
    @Override
    public OnBoardingSliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOnBoardingBinding binding = ItemOnBoardingBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new OnBoardingSliderViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OnBoardingSliderViewHolder holder, int position) {
        holder.createItem(onBoardings.get(position));
    }

    @Override
    public int getItemCount() {
        if (onBoardings!=null){
            return onBoardings.size();
        }
        return 0 ;
    }}
