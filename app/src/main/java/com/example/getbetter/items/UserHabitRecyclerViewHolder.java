package com.example.getbetter.items;

import android.annotation.SuppressLint;
import android.os.Build;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.getbetter.R;
import com.example.getbetter.databinding.ItemUserHabitBinding;
import com.example.getbetter.model.UserHabit;

import java.util.Calendar;

public class UserHabitRecyclerViewHolder extends RecyclerView.ViewHolder {

    public UserHabit userHabit;

    public TextView name;
    public TextView duration;

    public UserHabitRecyclerViewHolder(@NonNull ItemUserHabitBinding binding) {
        super(binding.getRoot());
        name = binding.itemHabitStatisticsName;
        duration = binding.itemHabitStatisticsDuration;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    public void createItem(UserHabit userHabit){
        this.userHabit = userHabit;
        name.setText(userHabit.getName_habit());
        int days = getDay(userHabit.getTimestamp());
        if (days > 90){
            duration.setTextColor(itemView.getResources().getColor(R.color.green));
            duration.setText("This habit has become a way of life for you");
        }
        else if (days >= 21){
            duration.setTextColor(itemView.getResources().getColor(R.color.secondary));
            duration.setText(90 - days + " Days To Habit It Becomes A Lifestyle");
        }
        else if (days < 20){
            duration.setTextColor(itemView.getResources().getColor(R.color.primary));
            duration.setText(21 - days +" Days To Become A Habit");
        }

        if (userHabit.getTimestamp_end() != ""){
            duration.setTextColor(itemView.getResources().getColor(R.color.red));
            duration.setText("It was just a try");
        }
    }

    private int getDay(String timestamp){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(Long.parseLong( timestamp ));
        int day = cal.get(Calendar.DAY_OF_YEAR);

        Calendar nowCal = Calendar.getInstance();
        int nowDay = nowCal.get(Calendar.DAY_OF_YEAR);

        return nowDay - day ;
    }
}