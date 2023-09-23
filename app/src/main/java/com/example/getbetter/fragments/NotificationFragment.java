package com.example.getbetter.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.getbetter.adapter.NotificationRecyclerAdapter;
import com.example.getbetter.databinding.FragmentNotificationBinding;
import com.example.getbetter.model.Notification;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class NotificationFragment extends Fragment {
    FragmentNotificationBinding binding ;


    private final ArrayList<Notification> notificationArrayList = new ArrayList<>();
    private FirebaseFirestore firebaseFirestore;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNotificationBinding.inflate(inflater,container,false) ;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        firebaseFirestore = FirebaseFirestore.getInstance();
        NotificationRecyclerAdapter notificationRecyclerAdapter = new NotificationRecyclerAdapter(notificationArrayList);

        firebaseFirestore.collection("notification")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            notificationArrayList.clear();
                            for (QueryDocumentSnapshot document : task.getResult()){
                                notificationArrayList.add( new Notification( document.getString("title") , document.getString("description") ));
                            }
                            notificationRecyclerAdapter.notifyDataSetChanged();

                        }
                    }
                });

        binding.notificationRecycler.setLayoutManager(new LinearLayoutManager(view.getContext(),RecyclerView.VERTICAL,false));
        binding.notificationRecycler.setAdapter(notificationRecyclerAdapter);
    }
}
