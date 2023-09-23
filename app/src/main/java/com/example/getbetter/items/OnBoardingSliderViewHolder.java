package com.example.getbetter.items;

import android.annotation.SuppressLint;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.getbetter.databinding.ItemOnBoardingBinding;
import com.example.getbetter.model.OnBoarding;

public class OnBoardingSliderViewHolder extends RecyclerView.ViewHolder {

    public OnBoarding onBoarding;

    public TextView title;
    public TextView description;
    public ImageView image;

    public OnBoardingSliderViewHolder(@NonNull ItemOnBoardingBinding binding) {
        super(binding.getRoot());
        title = binding.itemOnBoardingTitle;
        description = binding.itemOnBoardingDescription;
        image =binding.itemOnBoardingImg;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void createItem(OnBoarding onBoarding){
        this.onBoarding = onBoarding;
        title.setText(onBoarding.getTitle());
        description.setText(onBoarding.getDescription());
        image.setImageDrawable(itemView.getResources().getDrawable(onBoarding.getImg()));
    }
}
