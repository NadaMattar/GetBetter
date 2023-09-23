package com.example.getbetter.items;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.getbetter.databinding.ItemNotificationBinding;
import com.example.getbetter.model.Notification;

public class NotificationRecyclerViewHolder extends RecyclerView.ViewHolder {

    public Notification notification;

    public TextView title;
    public TextView description;

    public NotificationRecyclerViewHolder(@NonNull ItemNotificationBinding binding) {
        super(binding.getRoot());
        title = binding.itemNotificationTitle;
        description = binding.itemNotificationDescription;
    }

    public void createItem(Notification notification){
        this.notification = notification;
        title.setText(notification.getTitle());
        description.setText(notification.getDescription());
    }
}
