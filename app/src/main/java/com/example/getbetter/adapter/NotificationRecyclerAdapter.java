package com.example.getbetter.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.getbetter.databinding.ItemNotificationBinding;
import com.example.getbetter.items.NotificationRecyclerViewHolder;
import com.example.getbetter.model.Notification;

import java.util.ArrayList;

public class NotificationRecyclerAdapter extends RecyclerView.Adapter<NotificationRecyclerViewHolder> {

    private ArrayList<Notification> notifications = new ArrayList<>();

    public NotificationRecyclerAdapter(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    @NonNull
    @Override
    public NotificationRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNotificationBinding binding = ItemNotificationBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new NotificationRecyclerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationRecyclerViewHolder holder, int position) {
        holder.createItem(notifications.get(position));
    }

    @Override
    public int getItemCount() {
        if (notifications!=null){
            return notifications.size();
        }
        return 0 ;
    }}
